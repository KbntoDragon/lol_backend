package com.neon.lolCitas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "boleta")
@Getter
@Setter
@NoArgsConstructor
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boleta_id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orden_id", nullable = false, unique = true)
    private Orden orden;

    /** Numero correlativo de la boleta. */
    @Column(nullable = false, unique = true)
    private Integer folio;

    @CreationTimestamp
    @Column(name = "fecha_emision", nullable = false, updatable = false)
    private OffsetDateTime fechaEmision;

    @Column(nullable = false)
    private Integer neto;

    /** 19% del neto. */
    @Column(nullable = false)
    private Integer iva;

    /** neto + iva (lo exige el CHECK boleta_total_ck). */
    @Column(nullable = false)
    private Integer total;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false, length = 20)
    private MetodoPago metodoPago;
}
