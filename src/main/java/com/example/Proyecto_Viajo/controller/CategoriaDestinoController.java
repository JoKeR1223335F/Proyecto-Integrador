package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.CategoriaDestino;
import com.example.Proyecto_Viajo.repository.CategoriaDestinoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias-destino")
public class CategoriaDestinoController
    extends CrudController<CategoriaDestino, String> {

  public CategoriaDestinoController(CategoriaDestinoRepository repository) {
    super(repository);
  }
}
