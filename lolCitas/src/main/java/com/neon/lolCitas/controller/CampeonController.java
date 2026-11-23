package com.neon.lolCitas.controller;

import com.neon.lolCitas.model.Campeon;
import com.neon.lolCitas.repository.CampeonRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campeones")
public class CampeonController {

	private final CampeonRepository repo;

	public CampeonController(CampeonRepository repo) {
		this.repo = repo;
	}

	/** GET /api/campeones  o  GET /api/campeones?raza=Yordles */
	@GetMapping
	public List<Campeon> listar(@RequestParam(required = false) String raza) {
		return raza == null ? repo.findAll(Sort.by("id")) : repo.findByRazaOrderByIdAsc(raza);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Campeon> obtener(@PathVariable Long id) {
		return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Campeon crear(@RequestBody Campeon campeon) {
		campeon.setId(null);
		return repo.save(campeon);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Campeon> actualizar(@PathVariable Long id, @RequestBody Campeon datos) {
		if (!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		datos.setId(id);
		return ResponseEntity.ok(repo.save(datos));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		if (!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		repo.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
