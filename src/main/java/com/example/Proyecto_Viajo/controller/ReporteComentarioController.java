package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.ReporteComentario;
import com.example.Proyecto_Viajo.repository.ReporteComentarioRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reportes-comentarios")
public class ReporteComentarioController
    extends CrudController<ReporteComentario, Long> {

  public ReporteComentarioController(
      ReporteComentarioRepository repository) {
    super(repository);
  }
}
