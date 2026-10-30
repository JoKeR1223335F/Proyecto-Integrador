package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.MotivoRechazo;
import com.example.Proyecto_Viajo.repository.MotivoRechazoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/motivos-rechazo")
public class MotivoRechazoController
    extends CrudController<MotivoRechazo, Integer> {

  public MotivoRechazoController(MotivoRechazoRepository repository) {
    super(repository);
  }
}
