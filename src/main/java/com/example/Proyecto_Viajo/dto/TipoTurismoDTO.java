
package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class TipoTurismoDTO {
  private String codigo;
  private String descripcion;
  private Short orden;
  private Boolean activo;
}
