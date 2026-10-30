package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.Administrador;
import com.example.Proyecto_Viajo.repository.AdministradorRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController
    extends CrudController<Administrador, Long> {

  public AdministradorController(AdministradorRepository repository) {
    super(repository);
  }
}
