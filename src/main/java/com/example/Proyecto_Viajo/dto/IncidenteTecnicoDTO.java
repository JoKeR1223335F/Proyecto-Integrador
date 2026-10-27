package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class IncidenteTecnicoDTO {
  private Long idIncidente;
  private Long idSolicitudRegistro;
  private String codigo;
  private String mensaje;
  private String detalleTecnico;
  private Boolean resuelto;
  private LocalDateTime ocurridoEn;
  private LocalDateTime resueltoEn;
}
