package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.TipoTurismo;
import com.example.Proyecto_Viajo.repository.TipoTurismoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tipos-turismo")
public class TipoTurismoController
    extends CrudController<TipoTurismo, String> {

  public TipoTurismoController(TipoTurismoRepository repository) {
    super(repository);
  }
}
