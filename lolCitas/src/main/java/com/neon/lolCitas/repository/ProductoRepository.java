package com.neon.lolCitas.repository;

import com.neon.lolCitas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
