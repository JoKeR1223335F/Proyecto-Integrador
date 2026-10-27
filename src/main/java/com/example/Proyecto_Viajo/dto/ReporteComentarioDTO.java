
package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReporteComentarioDTO {
  private Long idReporte;
  private Long idComentario;
  private Long idVisitante;
  private Integer idMotivoRechazo;
  private String detalle;
  private String estado;
  private LocalDateTime creadoEn;
}
