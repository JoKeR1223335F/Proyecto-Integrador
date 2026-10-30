package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.PreferenciaVisitante;
import com.example.Proyecto_Viajo.repository.PreferenciaVisitanteRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/preferencias-visitante")
public class PreferenciaVisitanteController
    extends CrudController<PreferenciaVisitante, Long> {

  public PreferenciaVisitanteController(
      PreferenciaVisitanteRepository repository) {
    super(repository);
  }
}
