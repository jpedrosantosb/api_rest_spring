package com.jpedrosantosb.projeto.crud.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	// 404 - recurso não encontrado
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<Map<String, String>> handleNotFound(RecursoNaoEncontradoException ex) {

		Map<String, String> erro = new HashMap<>();
		erro.put("erro", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	// 400 - erro de validação
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {

		Map<String, String> erros = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(e -> erros.put(e.getField(), e.getDefaultMessage()));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
	}

	// 500 - erro genérico
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGeral(Exception ex) {

		Map<String, String> erro = new HashMap<>();
		erro.put("erro", "Erro interno no servidor");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}
}
