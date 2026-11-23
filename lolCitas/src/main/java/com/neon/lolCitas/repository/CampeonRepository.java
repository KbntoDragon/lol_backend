package com.neon.lolCitas.repository;

import com.neon.lolCitas.model.Campeon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampeonRepository extends JpaRepository<Campeon, Long> {

	List<Campeon> findByCategoriaId(Long categoriaId);
}
