package com.neon.lolCitas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** Clave primaria compuesta de imagen_campeon. */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagenCampeonId implements Serializable {

    @Column(name = "imagen_id")
    private Integer imagenId;

    @Column(name = "campeon_id")
    private Integer campeonId;
}
