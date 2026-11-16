package com.neon.lolCitas.repository;

import com.neon.lolCitas.model.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoletaRepository extends JpaRepository<Boleta, Integer> {

    Optional<Boleta> findByOrden_Id(Integer ordenId);
}
