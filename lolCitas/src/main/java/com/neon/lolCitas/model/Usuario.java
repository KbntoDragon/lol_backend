package com.neon.lolCitas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Usuario registrado. La contrasena se guarda cifrada con BCrypt,
 * y esta entidad nunca se devuelve directamente en la API (se usa UsuarioResponse).
 */
@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Nombre de invocador, ej: AhriMain#LAS */
	@Column(nullable = false, length = 50)
	private String nombre;

	/** Siempre en minusculas */
	@Column(nullable = false, unique = true)
	private String correo;

	/** Hash BCrypt, nunca la contrasena en texto plano */
	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Rol rol = Rol.USUARIO;

	@Column(nullable = false)
	private LocalDateTime fechaRegistro = LocalDateTime.now();
}
