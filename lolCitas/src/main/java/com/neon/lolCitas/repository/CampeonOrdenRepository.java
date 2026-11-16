package com.neon.lolCitas.repository;

import com.neon.lolCitas.model.CampeonOrden;
import com.neon.lolCitas.model.CampeonOrdenId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampeonOrdenRepository extends JpaRepository<CampeonOrden, CampeonOrdenId> {

    List<CampeonOrden> findByOrden_Id(Integer ordenId);
}
