package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import tools.jackson.databind.JsonNode;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BitacoraProcesoDTO {
  private Long idBitacora;
  private Long idSolicitudRegistro;
  private Long idUsuario;
  private String proceso;
  private String evento;
  private String nivel;
  private String descripcion;
  private JsonNode metadatos;
  private LocalDateTime creadoEn;
}
