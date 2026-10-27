
package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PublicacionDTO {
  private Long idPublicacion;
  private Long idDestino;
  private Long idVisitante;
  private String titulo;
  private String contenido;
  private Integer idEstadoModeracion;
  private LocalDateTime publicadaEn;
  private LocalDateTime creadoEn;
  private LocalDateTime actualizadoEn;
}
