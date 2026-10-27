package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class EstadoModeracionDTO {
  private Integer idEstado;
  private String nombreEstado;
  private String descripcion;
}
