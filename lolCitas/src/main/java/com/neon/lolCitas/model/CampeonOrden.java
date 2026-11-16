package com.neon.lolCitas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/** Detalle de la orden: cada fila es UN campeon arrendado por un periodo. */
@Entity
@Table(name = "campeon_orden")
@Getter
@Setter
@NoArgsConstructor
public class CampeonOrden {

    @EmbeddedId
    private CampeonOrdenId id = new CampeonOrdenId();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("campeonId")
    @JoinColumn(name = "campeon_id")
    private Campeon campeon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("ordenId")
    @JoinColumn(name = "orden_id")
    private Orden orden;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    /** Copia del precio del campeon al momento de arrendar. */
    @Column(name = "precio_dia", nullable = false)
    private Integer precioDia;

    /** precio_dia * cantidad de dias del periodo. */
    @Column(nullable = false)
    private Integer subtotal;
}
