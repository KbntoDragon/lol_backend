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
@Table(name = "campeon")
@Getter
@Setter
@NoArgsConstructor
public class Campeon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campeon_id")
    private Integer id;

    @Column(nullable = false, unique = true, length = 60)
    private String nombre;

    @Column(length = 100)
    private String titulo;

    @Column(columnDefinition = "text")
    private String descripcion;

    /** 1 = facil, 2 = media, 3 = dificil */
    private Short dificultad;

    /** Precio actual de arriendo por dia, en CLP. */
    @Column(name = "precio_dia", nullable = false)
    private Integer precioDia;

    @Column(nullable = false)
    private Boolean disponible = true;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private OffsetDateTime fechaCreacion;

    // Tabla intermedia categoria_campeon (N:M sin columnas extra)
    @ManyToMany
    @JoinTable(
            name = "categoria_campeon",
            joinColumns = @JoinColumn(name = "campeon_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categorias = new HashSet<>();

    // Tabla intermedia imagen_campeon (N:M con la columna es_principal)
    @OneToMany(mappedBy = "campeon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ImagenCampeon> imagenes = new HashSet<>();
}
