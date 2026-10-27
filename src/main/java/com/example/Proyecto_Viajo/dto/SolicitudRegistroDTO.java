
package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import tools.jackson.databind.JsonNode;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SolicitudRegistroDTO {
  private Long idSolicitudRegistro;
  private Long idUsuario;
  private String codigoEstadoRegistro;
  private String correo;
  private JsonNode datosEnviados;
  private Integer intentos;
  private LocalDateTime fechaCreacion;
  private LocalDateTime fechaUltimaActividad;
  private LocalDateTime fechaFinalizacion;
  private String tokenConfirmacionHash;
  private LocalDateTime expiraEn;
  private String observaciones;
}
