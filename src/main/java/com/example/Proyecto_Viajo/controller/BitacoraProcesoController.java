package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.model.BitacoraProceso;
import com.example.Proyecto_Viajo.repository.BitacoraProcesoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bitacora-procesos")
public class BitacoraProcesoController
    extends CrudController<BitacoraProceso, Long> {

  public BitacoraProcesoController(BitacoraProcesoRepository repository) {
    super(repository);
  }
}
