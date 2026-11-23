package com.neon.lolCitas.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ManejadorErrores {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<Map<String, String>> manejar(ApiException ex) {
		Map<String, String> body = new LinkedHashMap<>();
		body.put("error", ex.getMessage());
		if (ex.getCampo() != null) {
			body.put("campo", ex.getCampo());
		}
		return ResponseEntity.status(ex.getStatus()).body(body);
	}
}
