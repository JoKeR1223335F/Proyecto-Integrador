package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.Visitante;
import com.example.Proyecto_Viajo.repository.VisitanteRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/visitantes")
public class VisitanteController
    extends CrudController<Visitante, Long> {

  public VisitanteController(VisitanteRepository repository) {
    super(repository);
  }
}
