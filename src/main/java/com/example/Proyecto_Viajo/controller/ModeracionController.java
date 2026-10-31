package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.Moderacion;
import com.example.Proyecto_Viajo.repository.ModeracionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moderaciones")
public class ModeracionController
    extends CrudController<Moderacion, Long> {

  public ModeracionController(
      ModeracionRepository repository) {
    super(repository);
  }
}
