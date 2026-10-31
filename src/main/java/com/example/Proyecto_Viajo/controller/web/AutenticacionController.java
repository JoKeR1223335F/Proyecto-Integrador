package com.example.Proyecto_Viajo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * AutenticacionController
 */
@Controller
public class AutenticacionController {

  @GetMapping("/login")
  public String login() {
    return "/features/auth/login";
  }

  @GetMapping("/registro")
  public String registro() {
    return "/features/auth/registro";
  }

}
