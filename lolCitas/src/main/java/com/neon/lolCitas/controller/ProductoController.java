package com.neon.lolCitas.controller;

import com.neon.lolCitas.model.Producto;
import com.neon.lolCitas.repository.ProductoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	private final ProductoRepository repo;

	public ProductoController(ProductoRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public List<Producto> listar() {
		return repo.findAll(Sort.by("id"));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Producto> obtener(@PathVariable Long id) {
		return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		producto.setId(null);
		return repo.save(producto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto datos) {
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
