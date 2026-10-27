package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class EstadoRegistroDTO {
  private String codigo;
  private String significado;
  private String accion;
  private Boolean esFinal;
}
