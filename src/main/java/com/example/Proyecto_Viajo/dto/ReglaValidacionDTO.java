package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ReglaValidacionDTO {
  private String campo;
  private String regla;
  private String mensaje;
}
