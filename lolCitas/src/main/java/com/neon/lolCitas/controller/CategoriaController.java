package com.neon.lolCitas.controller;

import com.neon.lolCitas.model.Categoria;
import com.neon.lolCitas.repository.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	private final CategoriaRepository repo;

	public CategoriaController(CategoriaRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public List<Categoria> listar() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> obtener(@PathVariable Long id) {
		return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria crear(@RequestBody Categoria categoria) {
		categoria.setId(null);
		return repo.save(categoria);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody Categoria datos) {
		return repo.findById(id).map(c -> {
			c.setNombre(datos.getNombre());
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
}
