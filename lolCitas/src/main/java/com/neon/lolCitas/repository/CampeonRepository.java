package com.neon.lolCitas.repository;

import com.neon.lolCitas.model.Campeon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CampeonRepository extends JpaRepository<Campeon, Integer> {

    Optional<Campeon> findByNombre(String nombre);

    List<Campeon> findByDisponibleTrue();

    List<Campeon> findByCategorias_Nombre(String nombreCategoria);
}
