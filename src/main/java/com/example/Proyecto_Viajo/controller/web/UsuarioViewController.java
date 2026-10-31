
package com.example.Proyecto_Viajo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioViewController {

  @GetMapping
  public String perfil(
      @RequestParam("id") Long idUsuario,
      Model model) {

    model.addAttribute("usuarioId", idUsuario);

    return "features/usuario/perfil";
  }

  @GetMapping("/editar")
  public String editarPerfil(
      @RequestParam("id") Long idUsuario,
      Model model) {

    model.addAttribute("usuarioId", idUsuario);

    return "features/usuario/editar-perfil";
  }

  @GetMapping("/preferencias")
  public String preferencias(
      @RequestParam("id") Long idUsuario,
      Model model) {

    model.addAttribute("usuarioId", idUsuario);

    return "features/usuario/preferencias";
  }
}
