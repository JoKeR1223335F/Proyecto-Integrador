package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ConfirmacionRegistroDTO {
  private Long idConfirmacion;
  private Long idSolicitudRegistro;
  private String tokenHash;
  private LocalDateTime enviadaEn;
  private LocalDateTime expiraEn;
  private LocalDateTime confirmadaEn;
  private String estado;
}
