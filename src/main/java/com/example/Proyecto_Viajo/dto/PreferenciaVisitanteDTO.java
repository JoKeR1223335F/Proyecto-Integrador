
package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PreferenciaVisitanteDTO {
  private Long idPreferencia;
  private Long idVisitante;
  private String codigoTipoTurismo;
  private LocalDateTime fechaInicio;
  private LocalDateTime fechaFin;
  private Boolean activa;
}
