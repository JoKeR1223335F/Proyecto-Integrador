
package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ErrorValidacionDTO {
  private Long idErrorValidacion;
  private Long idSolicitudRegistro;
  private String idCampoFormulario;
  private String campoReglaValidacion;
  private String codigoError;
  private String mensaje;
  private String valorRecibido;
  private LocalDateTime creadoEn;
}
