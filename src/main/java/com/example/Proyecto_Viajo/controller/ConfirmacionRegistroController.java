package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.ConfirmacionRegistro;
import com.example.Proyecto_Viajo.repository.ConfirmacionRegistroRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/confirmaciones-registro")
public class ConfirmacionRegistroController
    extends CrudController<ConfirmacionRegistro, Long> {

  public ConfirmacionRegistroController(
      ConfirmacionRegistroRepository repository) {
    super(repository);
  }
}
