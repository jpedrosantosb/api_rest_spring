package com.jpedrosantosb.projeto.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpedrosantosb.projeto.crud.dto.UsuarioRequest;
import com.jpedrosantosb.projeto.crud.dto.UsuarioResponse;
import com.jpedrosantosb.projeto.crud.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public ResponseEntity<List<UsuarioResponse>> listar() {
		return ResponseEntity.ok(service.listar());
	}

	@PostMapping
	public ResponseEntity<UsuarioResponse> salvar(@RequestBody @Valid UsuarioRequest usuario) {
		return ResponseEntity.status(201).body(service.salvar(usuario));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioRequest usuario) {
		return ResponseEntity.ok(service.atualizar(id, usuario));
	}

}
