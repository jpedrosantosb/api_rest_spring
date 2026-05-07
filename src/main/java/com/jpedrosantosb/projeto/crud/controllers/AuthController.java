package com.jpedrosantosb.projeto.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpedrosantosb.projeto.crud.dto.LoginRequest;
import com.jpedrosantosb.projeto.crud.entities.Usuario;
import com.jpedrosantosb.projeto.crud.repositories.UsuarioRepository;
import com.jpedrosantosb.projeto.crud.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UsuarioRepository repository;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {

		Usuario usuario = repository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		if (!usuario.getSenha().equals(request.getSenha())) {
			return ResponseEntity.status(401).body("Senha inválida");
		}

		String token = JwtUtil.gerarToken(usuario.getEmail());

		return ResponseEntity.ok(token);
	}

}
