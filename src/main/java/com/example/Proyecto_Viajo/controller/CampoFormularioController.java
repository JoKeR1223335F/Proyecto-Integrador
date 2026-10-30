package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.CampoFormulario;
import com.example.Proyecto_Viajo.repository.CampoFormularioRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/campos-formulario")
public class CampoFormularioController
    extends CrudController<CampoFormulario, String> {

  public CampoFormularioController(CampoFormularioRepository repository) {
    super(repository);
  }
}
