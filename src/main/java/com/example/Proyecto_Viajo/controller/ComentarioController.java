package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.Comentario;
import com.example.Proyecto_Viajo.repository.ComentarioRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController
    extends CrudController<Comentario, Long> {

  public ComentarioController(
      ComentarioRepository repository) {
    super(repository);
  }
}
