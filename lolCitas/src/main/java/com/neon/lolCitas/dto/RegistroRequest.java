package com.neon.lolCitas.dto;

/** Body de POST /api/usuarios/registro */
public record RegistroRequest(String nombre, String correo, String password) {
}
