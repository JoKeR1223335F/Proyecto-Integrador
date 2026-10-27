
package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.Rol;
import com.example.Proyecto_Viajo.repository.RolRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RolController extends CrudController<Rol, Integer> {

  public RolController(RolRepository repository) {
    super(repository);
  }
}
