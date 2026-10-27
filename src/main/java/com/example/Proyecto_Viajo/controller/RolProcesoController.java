package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.RolProceso;
import com.example.Proyecto_Viajo.repository.RolProcesoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles-proceso")
public class RolProcesoController
    extends CrudController<RolProceso, String> {

  public RolProcesoController(RolProcesoRepository repository) {
    super(repository);
  }
}
