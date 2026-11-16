package com.neon.lolCitas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orden")
@Getter
@Setter
@NoArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orden_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @CreationTimestamp
    @Column(name = "fecha_orden", nullable = false, updatable = false)
    private OffsetDateTime fechaOrden;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoOrden estado = EstadoOrden.PENDIENTE;

    /** Suma de los subtotales del detalle. */
    @Column(nullable = false)
    private Integer total = 0;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CampeonOrden> detalles = new HashSet<>();

    // 1:1 -> una orden tiene como maximo una boleta
    @OneToOne(mappedBy = "orden", fetch = FetchType.LAZY)
    private Boleta boleta;
}
