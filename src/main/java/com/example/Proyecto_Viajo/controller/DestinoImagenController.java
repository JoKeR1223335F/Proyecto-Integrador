
package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.model.DestinoImagen;
import com.example.Proyecto_Viajo.service.DestinoImagenService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RestController
@RequestMapping("/api/destino-imagenes")
@CrossOrigin(origins = "*")
public class DestinoImagenController {

  private final DestinoImagenService imagenService;

  public DestinoImagenController(
      DestinoImagenService imagenService) {
    this.imagenService = imagenService;
  }

  // SUBIR IMAGEN
  @PostMapping(value = "/subir", consumes = "multipart/form-data")
  public ResponseEntity<DestinoImagen> subirImagen(

      @RequestParam("archivo") MultipartFile archivo,
      @RequestParam("idDestino") Long idDestino,
      @RequestParam(value = "textoAlternativo", required = false) String textoAlternativo,
      @RequestParam(value = "esPrincipal", defaultValue = "false") Boolean esPrincipal,
      @RequestParam(value = "orden", defaultValue = "0") Integer orden

  ) {

    try {

      DestinoImagen imagen = imagenService.subirImagen(
          archivo,
          idDestino,
          textoAlternativo,
          esPrincipal,
          orden);

      return ResponseEntity.status(HttpStatus.CREATED)
          .body(imagen);

    } catch (IllegalArgumentException e) {

      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          e.getMessage());
    }
  }

  // LISTAR IMAGENES POR DESTINO
  @GetMapping("/destino/{idDestino}")
  @Transactional(readOnly = true)
  public ResponseEntity<List<DestinoImagen>> listarPorDestino(
      @PathVariable Long idDestino) {

    return ResponseEntity.ok(
        imagenService.listarPorDestino(idDestino));
  }

  // BUSCAR IMAGEN POR ID
  @GetMapping("/{id}")
  @Transactional(readOnly = true)
  public ResponseEntity<DestinoImagen> buscarPorId(
      @PathVariable Long id) {

    try {

      return ResponseEntity.ok(
          imagenService.buscarPorId(id));

    } catch (IllegalArgumentException e) {

      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          e.getMessage());
    }
  }
}
