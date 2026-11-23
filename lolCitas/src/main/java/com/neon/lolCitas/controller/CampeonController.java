package com.neon.lolCitas.controller;

import com.neon.lolCitas.model.Campeon;
import com.neon.lolCitas.repository.CampeonRepository;
import com.neon.lolCitas.repository.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campeones")
public class CampeonController {

	private final CampeonRepository repo;
	private final CategoriaRepository categoriaRepo;

	public CampeonController(CampeonRepository repo, CategoriaRepository categoriaRepo) {
		this.repo = repo;
		this.categoriaRepo = categoriaRepo;
	}

	/** GET /api/campeones  o  GET /api/campeones?categoriaId=1 */
	@GetMapping
	public List<Campeon> listar(@RequestParam(required = false) Long categoriaId) {
		return categoriaId == null ? repo.findAll() : repo.findByCategoriaId(categoriaId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Campeon> obtener(@PathVariable Long id) {
		return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	/** Body: {"nombre":"Ahri","titulo":"...","descripcion":"...","imagenUrl":"...","categoria":{"id":1}} */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Campeon crear(@RequestBody Campeon campeon) {
		campeon.setId(null);
		resolverCategoria(campeon);
		return repo.save(campeon);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Campeon> actualizar(@PathVariable Long id, @RequestBody Campeon datos) {
		return repo.findById(id).map(c -> {
			c.setNombre(datos.getNombre());
			c.setTitulo(datos.getTitulo());
			c.setDescripcion(datos.getDescripcion());
			c.setImagenUrl(datos.getImagenUrl());
			c.setCategoria(datos.getCategoria());
			resolverCategoria(c);
			return ResponseEntity.ok(repo.save(c));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		if (!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		repo.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	private void resolverCategoria(Campeon c) {
		if (c.getCategoria() != null && c.getCategoria().getId() != null) {
			c.setCategoria(categoriaRepo.findById(c.getCategoria().getId()).orElse(null));
		} else {
			c.setCategoria(null);
		}
	}
}
