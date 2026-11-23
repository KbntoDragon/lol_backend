package com.neon.lolCitas.dto;

/** Body de POST /api/usuarios/login */
public record LoginRequest(String correo, String password) {
}
