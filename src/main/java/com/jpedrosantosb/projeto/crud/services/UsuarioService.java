package com.jpedrosantosb.projeto.crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpedrosantosb.projeto.crud.entities.Usuario;
import com.jpedrosantosb.projeto.crud.exceptions.RecursoNaoEncontradoException;
import com.jpedrosantosb.projeto.crud.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> listar() {
		return repository.findAll();
	}

	public Usuario salvar(Usuario usuario) {
		return repository.save(usuario);
	}

	public void deletar(Long id) {
		if (!repository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id);
		}
		repository.deleteById(id);
	}

	public Usuario atualizar(Long id, Usuario usuarioAtualizado) {

		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id));

		usuario.setNome(usuarioAtualizado.getNome());
		usuario.setEmail(usuarioAtualizado.getEmail());

		if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isBlank()) {
			usuario.setSenha(usuarioAtualizado.getSenha());
		}

		return repository.save(usuario);
	}
}
