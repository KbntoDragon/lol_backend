package com.neon.lolCitas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** Clave primaria compuesta de campeon_orden. */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampeonOrdenId implements Serializable {

    @Column(name = "campeon_id")
    private Integer campeonId;

    @Column(name = "orden_id")
    private Integer ordenId;
}
