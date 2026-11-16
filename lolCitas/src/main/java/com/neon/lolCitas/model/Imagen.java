package com.neon.lolCitas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "imagen")
@Getter
@Setter
@NoArgsConstructor
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imagen_id")
    private Integer id;

    @Column(nullable = false, length = 500)
    private String url;

    @Column(name = "texto_alt", length = 150)
    private String textoAlt;

    @CreationTimestamp
    @Column(name = "fecha_subida", nullable = false, updatable = false)
    private OffsetDateTime fechaSubida;
}
