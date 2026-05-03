package com.jpedrosantosb.projeto.crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpedrosantosb.projeto.crud.dto.UsuarioRequest;
import com.jpedrosantosb.projeto.crud.dto.UsuarioResponse;
import com.jpedrosantosb.projeto.crud.entities.Usuario;
import com.jpedrosantosb.projeto.crud.exceptions.RecursoNaoEncontradoException;
import com.jpedrosantosb.projeto.crud.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<UsuarioResponse> listar() {
		return repository.findAll().stream().map(this::toResponse).toList();
	}

	public UsuarioResponse salvar(UsuarioRequest request) {

		Usuario usuario = new Usuario();
		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());
		usuario.setSenha(request.getSenha());

		return toResponse(repository.save(usuario));
	}

	public void deletar(Long id) {
		if (!repository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id);
		}
		repository.deleteById(id);
	}

	public UsuarioResponse atualizar(Long id, UsuarioRequest request) {

		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id));

		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());

		if (request.getSenha() != null && !request.getSenha().isBlank()) {
			usuario.setSenha(request.getSenha());
		}

		return toResponse(repository.save(usuario));
	}

	private UsuarioResponse toResponse(Usuario usuario) {
		return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail());
	}
}
