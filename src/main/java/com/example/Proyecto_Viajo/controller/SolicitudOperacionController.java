package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.model.SolicitudOperacion;
import com.example.Proyecto_Viajo.service.SolicitudOperacionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes-operacion")
@CrossOrigin(origins = "*")
public class SolicitudOperacionController {

  private final SolicitudOperacionService solicitudService;

  public SolicitudOperacionController(
      SolicitudOperacionService solicitudService) {
    this.solicitudService = solicitudService;
  }

  // LISTAR TODAS
  @GetMapping
  public ResponseEntity<List<SolicitudOperacion>> listarTodos() {
    return ResponseEntity.ok(solicitudService.listarTodos());
  }

  // BUSCAR POR ID
  @GetMapping("/{id}")
  public ResponseEntity<SolicitudOperacion> buscarPorId(
      @PathVariable Long id) {
    try {
      return ResponseEntity.ok(
          solicitudService.buscarPorId(id));
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          e.getMessage());
    }
  }

  // CREAR SOLICITUD
  @PostMapping
  public ResponseEntity<SolicitudOperacion> crear(
      @RequestBody SolicitudOperacionRequest request) {
    try {
      SolicitudOperacion solicitud = solicitudService.crear(
          request.idDestino(),
          request.tipoOperacion(),
          request.motivo());

      return ResponseEntity.status(HttpStatus.CREATED)
          .body(solicitud);

    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          e.getMessage());
    }
  }

  // RESOLVER SOLICITUD
  @PutMapping("/{id}/resolver")
  public ResponseEntity<SolicitudOperacion> resolver(
      @PathVariable Long id,
      @RequestBody ResolverSolicitudRequest request) {
    try {
      return ResponseEntity.ok(
          solicitudService.resolver(
              id,
              request.idAdministrador(),
              request.estado(),
              request.observaciones()));

    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          e.getMessage());
    }
  }

  // LISTAR POR DESTINO
  @GetMapping("/destino/{idDestino}")
  public ResponseEntity<List<SolicitudOperacion>> listarPorDestino(
      @PathVariable Long idDestino) {
    return ResponseEntity.ok(
        solicitudService.listarPorDestino(idDestino));
  }

  // LISTAR POR ESTADO
  @GetMapping("/estado/{estado}")
  public ResponseEntity<List<SolicitudOperacion>> listarPorEstado(
      @PathVariable String estado) {
    try {
      return ResponseEntity.ok(
          solicitudService.listarPorEstado(estado));
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          e.getMessage());
    }
  }

  // LISTAR POR DESTINO Y ESTADO
  @GetMapping("/destino/{idDestino}/estado/{estado}")
  public ResponseEntity<List<SolicitudOperacion>> listarPorDestinoYEstado(
      @PathVariable Long idDestino,
      @PathVariable String estado) {

    try {
      return ResponseEntity.ok(
          solicitudService.listarPorDestinoYEstado(
              idDestino,
              estado));
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          e.getMessage());
    }
  }

  // ELIMINAR
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    try {
      solicitudService.eliminar(id);
      return ResponseEntity.noContent().build();

    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          e.getMessage());
    }
  }

  // DTO PARA CREAR SOLICITUD
  public record SolicitudOperacionRequest(
      Long idDestino,
      String tipoOperacion,
      String motivo) {
  }

  // DTO PARA RESOLVER SOLICITUD
  public record ResolverSolicitudRequest(
      Long idAdministrador,
      String estado,
      String observaciones) {
  }
}
