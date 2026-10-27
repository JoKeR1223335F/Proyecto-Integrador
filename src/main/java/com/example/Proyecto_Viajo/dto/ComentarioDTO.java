
package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ComentarioDTO {
  private Long idComentario;
  private Long idPublicacion;
  private Long idVisitante;
  private String contenido;
  private Integer idEstadoModeracion;
  private LocalDateTime creadoEn;
  private LocalDateTime actualizadoEn;
}
