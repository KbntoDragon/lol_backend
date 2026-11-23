package com.neon.lolCitas.controller;

import com.neon.lolCitas.dto.LoginRequest;
import com.neon.lolCitas.dto.RegistroRequest;
import com.neon.lolCitas.dto.UsuarioResponse;
import com.neon.lolCitas.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioService service;

	public UsuarioController(UsuarioService service) {
		this.service = service;
	}

	/**
	 * Body: {"nombre":"AhriMain#LAS","correo":"ahri@correo.com","password":"Clave123"}
	 * 201 -> {"id":1,"nombre":"...","correo":"...","rol":"USUARIO"}
	 * 400 -> datos invalidos, 409 -> correo ya registrado
	 */
	@PostMapping("/registro")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioResponse registrar(@RequestBody RegistroRequest req) {
		return service.registrar(req);
	}

	/**
	 * Body: {"correo":"ahri@correo.com","password":"Clave123"}
	 * 200 -> {"id":1,"nombre":"...","correo":"...","rol":"USUARIO"}
	 * 401 -> correo o contrasena incorrectos
	 */
	@PostMapping("/login")
	public UsuarioResponse login(@RequestBody LoginRequest req) {
		return service.login(req);
	}
}
