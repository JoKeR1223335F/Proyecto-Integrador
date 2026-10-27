package com.example.Proyecto_Viajo.controller.shared;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class CrudController<E, ID> {

  protected final JpaRepository<E, ID> repository;

  protected CrudController(JpaRepository<E, ID> repository) {
    this.repository = repository;
  }

  @GetMapping
  public ResponseEntity<List<E>> listar() {
    return ResponseEntity.ok(repository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<E> buscarPorId(@PathVariable ID id) {
    return repository.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<?> crear(@RequestBody E entidad) {

    if (entidad instanceof com.example.Proyecto_Viajo.model.Rol rol) {
      System.out.println("nombreRol recibido: " + rol.getNombreRol());

      if (rol.getNombreRol() == null || rol.getNombreRol().isBlank()) {
        return ResponseEntity.badRequest()
            .body("El campo nombreRol es obligatorio.");
      }
    }

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(repository.save(entidad));
  }

  @PutMapping("/{id}")
  public ResponseEntity<E> actualizar(
      @PathVariable ID id,
      @RequestBody E entidad) {

    if (!repository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(repository.save(entidad));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable ID id) {
    if (!repository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }

    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
