package com.neon.lolCitas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "imagen_campeon")
@Getter
@Setter
@NoArgsConstructor
public class ImagenCampeon {

    @EmbeddedId
    private ImagenCampeonId id = new ImagenCampeonId();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("imagenId")
    @JoinColumn(name = "imagen_id")
    private Imagen imagen;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("campeonId")
    @JoinColumn(name = "campeon_id")
    private Campeon campeon;

    /** Marca la imagen que se muestra en la tarjeta del campeon. */
    @Column(name = "es_principal", nullable = false)
    private Boolean esPrincipal = false;

    public ImagenCampeon(Imagen imagen, Campeon campeon, boolean esPrincipal) {
        this.imagen = imagen;
        this.campeon = campeon;
        this.esPrincipal = esPrincipal;
    }
}
