package com.neon.lolCitas.repository;

import com.neon.lolCitas.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByNombre(String nombre);
}
