package com.neon.lolCitas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Campeon que aparece en la app de citas.
 * El JSON tiene la misma forma que usa el frontend (src/data/personajes.js):
 * { id, nombre, raza, distancia_km, descripcion, instagram, imagen, posicion }
 */
@Entity
@Table(name = "campeones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campeon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	/** Humanos, Vastayas, Del Vacío, Yordles... */
	private String raza;

	@JsonProperty("distancia_km")
	private Integer distanciaKm;

	@Column(length = 2000)
	private String descripcion;

	private String instagram;

	@Column(length = 1000)
	private String imagen;

	/** object-position CSS para encuadrar la foto, ej: "50% 20%" */
	private String posicion;
}
