package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.Destino;
import com.example.Proyecto_Viajo.repository.DestinoRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController extends CrudController<Destino, Long> {

  public DestinoController(DestinoRepository repository) {
    super(repository);
  }
}
