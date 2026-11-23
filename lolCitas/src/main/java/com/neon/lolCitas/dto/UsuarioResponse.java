package com.neon.lolCitas.dto;

import com.neon.lolCitas.model.Rol;
import com.neon.lolCitas.model.Usuario;

/** Lo que la API devuelve de un usuario (sin la contrasena). */
public record UsuarioResponse(Long id, String nombre, String correo, Rol rol) {

	public static UsuarioResponse de(Usuario u) {
		return new UsuarioResponse(u.getId(), u.getNombre(), u.getCorreo(), u.getRol());
	}
}
