package com.neon.lolCitas.repository;

import com.neon.lolCitas.model.ImagenCampeon;
import com.neon.lolCitas.model.ImagenCampeonId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagenCampeonRepository extends JpaRepository<ImagenCampeon, ImagenCampeonId> {

    List<ImagenCampeon> findByCampeon_Id(Integer campeonId);
}
