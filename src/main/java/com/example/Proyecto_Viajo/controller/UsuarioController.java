package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.Usuario;
import com.example.Proyecto_Viajo.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController
    extends CrudController<Usuario, Long> {

  public UsuarioController(UsuarioRepository repository) {
    super(repository);
  }
}
