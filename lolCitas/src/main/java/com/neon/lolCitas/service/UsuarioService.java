package com.neon.lolCitas.service;

import com.neon.lolCitas.dto.LoginRequest;
import com.neon.lolCitas.dto.RegistroRequest;
import com.neon.lolCitas.dto.UsuarioResponse;
import com.neon.lolCitas.exception.ApiException;
import com.neon.lolCitas.model.Rol;
import com.neon.lolCitas.model.Usuario;
import com.neon.lolCitas.repository.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class UsuarioService {

	// Mismas reglas que el formulario del frontend (Registro.jsx)
	private static final Pattern REGEX_CORREO = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
	private static final Pattern REGEX_PASSWORD = Pattern.compile("^(?=.*[A-Z])(?=.*\\d).{6,}$");

	private final UsuarioRepository repo;
	private final PasswordEncoder encoder;

	/** Hash de relleno para que el login tarde lo mismo exista o no el correo */
	private final String hashRelleno;

	public UsuarioService(UsuarioRepository repo, PasswordEncoder encoder) {
		this.repo = repo;
		this.encoder = encoder;
		this.hashRelleno = encoder.encode("relleno-para-tiempo-constante");
	}

	public UsuarioResponse registrar(RegistroRequest req) {
		String nombre = req.nombre() == null ? "" : req.nombre().trim();
		String correo = normalizarCorreo(req.correo());
		String password = req.password() == null ? "" : req.password();

		if (nombre.length() < 3 || nombre.length() > 50) {
			throw new ApiException(HttpStatus.BAD_REQUEST,
					"El nombre de invocador debe tener entre 3 y 50 caracteres.", "nombre");
		}
		if (!REGEX_CORREO.matcher(correo).matches() || correo.length() > 255) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Ingresa un correo electrónico válido.", "correo");
		}
		if (!REGEX_PASSWORD.matcher(password).matches()) {
			throw new ApiException(HttpStatus.BAD_REQUEST,
					"La contraseña debe tener mínimo 6 caracteres, una mayúscula y un número.", "password");
		}
		// BCrypt solo usa los primeros 72 bytes
		if (password.getBytes(StandardCharsets.UTF_8).length > 72) {
			throw new ApiException(HttpStatus.BAD_REQUEST,
					"La contraseña es demasiado larga (máximo 72 caracteres).", "password");
		}
		if (repo.existsByCorreo(correo)) {
			throw correoRepetido();
		}

		Usuario u = new Usuario();
		u.setNombre(nombre);
		u.setCorreo(correo);
		u.setPassword(encoder.encode(password));
		u.setRol(Rol.USUARIO);

		try {
			return UsuarioResponse.de(repo.save(u));
		} catch (DataIntegrityViolationException e) {
			// Dos registros con el mismo correo a la vez
			throw correoRepetido();
		}
	}

	public UsuarioResponse login(LoginRequest req) {
		String correo = normalizarCorreo(req.correo());
		String password = req.password() == null ? "" : req.password();

		Usuario u = repo.findByCorreo(correo).orElse(null);
		String hash = u != null ? u.getPassword() : hashRelleno;
		boolean ok = encoder.matches(password, hash);

		if (u == null || !ok) {
			throw new ApiException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos.", null);
		}
		return UsuarioResponse.de(u);
	}

	public static String normalizarCorreo(String correo) {
		return correo == null ? "" : correo.trim().toLowerCase(Locale.ROOT);
	}

	private static ApiException correoRepetido() {
		return new ApiException(HttpStatus.CONFLICT, "Ese correo ya está registrado.", "correo");
	}
}
