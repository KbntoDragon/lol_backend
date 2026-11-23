package com.neon.lolCitas.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Error que se devuelve al frontend como JSON: { "error": "...", "campo": "correo" }.
 * "campo" indica que input del formulario tiene el problema (puede ser null).
 */
@Getter
public class ApiException extends RuntimeException {

	private final HttpStatus status;
	private final String campo;

	public ApiException(HttpStatus status, String mensaje, String campo) {
		super(mensaje);
		this.status = status;
		this.campo = campo;
	}
}
