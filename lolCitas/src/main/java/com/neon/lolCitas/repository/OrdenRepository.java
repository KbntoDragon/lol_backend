package com.neon.lolCitas.repository;

import com.neon.lolCitas.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenRepository extends JpaRepository<Orden, Integer> {

    List<Orden> findByUsuario_Id(Integer usuarioId);
}
