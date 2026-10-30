package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.EstadoRegistro;
import com.example.Proyecto_Viajo.repository.EstadoRegistroRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estados-registro")
public class EstadoRegistroController
    extends CrudController<EstadoRegistro, String> {

  public EstadoRegistroController(EstadoRegistroRepository repository) {
    super(repository);
  }
}
