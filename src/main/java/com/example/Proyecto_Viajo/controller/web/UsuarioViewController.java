package com.example.Proyecto_Viajo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioViewController {

  @GetMapping("/usuario")
  public String usuario() {
    return "feature/usuario/usuario";
  }
}
