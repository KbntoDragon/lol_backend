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
 * Regalo de la tienda.
 * El JSON tiene la misma forma que usa el frontend (src/data/productos.js):
 * { id, nombre, precio_oro, imagen, descripcion, mensaje, ideal_para, impresion }
 */
@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@JsonProperty("precio_oro")
	private Integer precioOro;

	@Column(length = 1000)
	private String imagen;

	@Column(length = 2000)
	private String descripcion;

	@Column(length = 1000)
	private String mensaje;

	@JsonProperty("ideal_para")
	private String idealPara;

	/** Nivel de impresion de 1 a 5 */
	private Integer impresion;
}
