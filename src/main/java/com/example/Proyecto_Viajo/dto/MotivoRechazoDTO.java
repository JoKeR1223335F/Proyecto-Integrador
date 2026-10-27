
package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class MotivoRechazoDTO {
  private Integer idMotivo;
  private String nombreMotivo;
  private String descripcion;
  private Short gravedad;
}
