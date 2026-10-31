package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.Notificacion;
import com.example.Proyecto_Viajo.repository.NotificacionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController
    extends CrudController<Notificacion, Long> {

  public NotificacionController(
      NotificacionRepository repository) {
    super(repository);
  }
}
