package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SolicitudOperacionDTO {
  private Long idSolicitudOperacion;
  private Long idDestino;
  private Long idAdministrador;
  private String tipoOperacion;
  private String estado;
  private String motivo;
  private String observaciones;
  private LocalDateTime solicitadaEn;
  private LocalDateTime resueltaEn;
}
