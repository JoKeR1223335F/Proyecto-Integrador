package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.ReglaValidacion;
import com.example.Proyecto_Viajo.repository.ReglaValidacionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reglas-validacion")
public class ReglaValidacionController
    extends CrudController<ReglaValidacion, String> {

  public ReglaValidacionController(ReglaValidacionRepository repository) {
    super(repository);
  }
}
