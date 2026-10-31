package com.example.Proyecto_Viajo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home() {
    return "home";
  }

  @GetMapping("/nosotros")
  public String nosotros() {
    return "/features/about/about";
  }

}
