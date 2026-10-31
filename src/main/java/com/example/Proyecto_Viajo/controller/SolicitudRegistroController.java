package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.SolicitudRegistro;
import com.example.Proyecto_Viajo.repository.SolicitudRegistroRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solicitudes-registro")
public class SolicitudRegistroController
    extends CrudController<SolicitudRegistro, Long> {

  public SolicitudRegistroController(
      SolicitudRegistroRepository repository) {
    super(repository);
  }
}
