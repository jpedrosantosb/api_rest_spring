package com.jpedrosantosb.projeto.crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpedrosantosb.projeto.crud.entities.Usuario;
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
}
