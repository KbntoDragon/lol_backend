package com.neon.lolCitas.config;

import com.neon.lolCitas.model.Campeon;
import com.neon.lolCitas.model.Producto;
import com.neon.lolCitas.model.Rol;
import com.neon.lolCitas.model.Usuario;
import com.neon.lolCitas.repository.CampeonRepository;
import com.neon.lolCitas.repository.ProductoRepository;
import com.neon.lolCitas.repository.UsuarioRepository;
import com.neon.lolCitas.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Al arrancar, si las tablas estan vacias, carga los datos que antes estaban
 * fijos en el frontend (src/data/personajes.js y src/data/productos.js).
 * Si ya hay datos, no hace nada.
 * Tambien crea la cuenta de administrador si ADMIN_PASSWORD esta configurada.
 */
@Component
public class DatosIniciales implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DatosIniciales.class);

	private final CampeonRepository campeones;
	private final ProductoRepository productos;
	private final UsuarioRepository usuarios;
	private final PasswordEncoder encoder;
	private final String adminCorreo;
	private final String adminPassword;

	public DatosIniciales(CampeonRepository campeones, ProductoRepository productos,
			UsuarioRepository usuarios, PasswordEncoder encoder,
			@Value("${app.admin.correo}") String adminCorreo,
			@Value("${app.admin.password}") String adminPassword) {
		this.campeones = campeones;
		this.productos = productos;
		this.usuarios = usuarios;
		this.encoder = encoder;
		this.adminCorreo = UsuarioService.normalizarCorreo(adminCorreo);
		this.adminPassword = adminPassword;
	}

	@Override
	public void run(String... args) {
		crearAdmin();

		if (campeones.count() == 0) {
			campeones.saveAll(List.of(
				new Campeon(null, "Ahri", "Vastayas", 8, "Misteriosa y encantadora.", "@ahri.en.la.grieta", "https://i.ibb.co/b5Jkgkc8/Ahri.webp", "50% 20%"),
				new Campeon(null, "Lux", "Humanos", 15, "Alegre y llena de luz.", "@lux.luminosa", "https://i.ibb.co/PZ3CZ14M/League-Infobox-Lux.jpg", "5% 30%"),
				new Campeon(null, "Kai'Sa", "Del Vacío", 22, "Independiente y decidida.", "@kaisa.del.vacio", "https://i.ibb.co/HL7ZvG0t/Kaisa-0.jpg", "center 0#"),
				new Campeon(null, "Vel'Koz", "Del Vacío", 60, "Curioso y muy inteligente.", "@velkoz.ojo", "https://i.ibb.co/qzdbwdN/Velkoz-0.jpg", null),
				new Campeon(null, "Teemo", "Yordles", 5, "Simpático y siempre alerta.", "@teemo.explorador", "https://i.ibb.co/5xWD3RrS/Teemo-0.jpg", null),
				new Campeon(null, "Lulu", "Yordles", 40, "Divertida y fantasiosa.", "@lulu.y.pix", "https://i.ibb.co/SDfmj1pY/Lulu-29.webp", "50% 40%"),
				new Campeon(null, "Neeko", "Vastayas", 18, "Sociable y llena de sorpresas.", "@neeko.camaleona", "https://i.ibb.co/NgQ8LKvS/League-Infobox-Neeko.jpg", "50% 20%"),
				new Campeon(null, "Garen", "Humanos", 75, "Noble y valiente.", "@garen.demacia", "https://i.ibb.co/60VH5kKg/Garen-0.jpg", "center 30%")
			));
			log.info("Datos iniciales: campeones cargados");
		}

		if (productos.count() == 0) {
			productos.saveAll(List.of(
				new Producto(null, "Espada de Doran", 450, "https://i.ibb.co/cdPspCF/Doran-s-Blade-old2.webp",
					"Una espada ligera y confiable, pensada para empezar bien la partida. No es un regalo ostentoso, pero se nota que lo elegiste con cabeza.",
					"Eres práctico y vas paso a paso.", "Primera cita tranquila", 2),
				new Producto(null, "Espadón", 1300, "https://i.ibb.co/D0gZYdj/B-F-Sword.webp",
					"Una hoja enorme y pesada que casi nadie usa sola, pero que es la base de armas legendarias. Es un regalo con ambición.",
					"Piensas en grande y apuestas por lo que viene.", "Segunda cita", 3),
				new Producto(null, "Filo del Infinito", 3500, "https://i.ibb.co/bjv1tfB9/Infinity-Edge.webp",
					"Una de las armas más codiciadas de la Grieta: lujo, precisión y golpes críticos. Nadie olvida a quien regala esto.",
					"Vas en serio y no escatimas.", "Para ganarte su corazón", 5)
			));
			log.info("Datos iniciales: productos cargados");
		}
	}

	private void crearAdmin() {
		if (adminPassword == null || adminPassword.isBlank()) {
			log.info("ADMIN_PASSWORD no configurada: no se crea la cuenta de administrador");
			return;
		}
		Usuario existente = usuarios.findByCorreo(adminCorreo).orElse(null);
		if (existente != null) {
			if (existente.getRol() != Rol.ADMIN) {
				log.warn("{} ya existe como usuario normal; no se convierte en admin automaticamente", adminCorreo);
			}
			return;
		}
		Usuario admin = new Usuario();
		admin.setNombre("Admin");
		admin.setCorreo(adminCorreo);
		admin.setPassword(encoder.encode(adminPassword));
		admin.setRol(Rol.ADMIN);
		usuarios.save(admin);
		log.info("Cuenta de administrador creada: {}", adminCorreo);
	}
}
