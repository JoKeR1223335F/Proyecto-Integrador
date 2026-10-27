
package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NotificacionDTO {
  private Long idNotificacion;
  private Long idVisitante;
  private Long idAdministrador;
  private String titulo;
  private String mensaje;
  private String tipo;
  private Boolean leida;
  private LocalDateTime creadoEn;
  private LocalDateTime leidaEn;
}
