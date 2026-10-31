package com.example.Proyecto_Viajo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistaController {

  @GetMapping("/destinos")
  public String destinos() {
    return "/features/destino/destinos";
  }

  @GetMapping("/publicaciones")
  public String publicaciones() {
    return "/features/publicacion/publicaciones";
  }
}
