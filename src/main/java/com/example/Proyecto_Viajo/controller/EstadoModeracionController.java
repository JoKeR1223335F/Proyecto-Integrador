package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.EstadoModeracion;
import com.example.Proyecto_Viajo.repository.EstadoModeracionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estados-moderacion")
public class EstadoModeracionController
    extends CrudController<EstadoModeracion, Integer> {

  public EstadoModeracionController(EstadoModeracionRepository repository) {
    super(repository);
  }
}
