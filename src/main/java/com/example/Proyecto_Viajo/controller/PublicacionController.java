package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.Publicacion;
import com.example.Proyecto_Viajo.repository.PublicacionRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController
    extends CrudController<Publicacion, Long> {

  public PublicacionController(
      PublicacionRepository repository) {
    super(repository);
  }
}
