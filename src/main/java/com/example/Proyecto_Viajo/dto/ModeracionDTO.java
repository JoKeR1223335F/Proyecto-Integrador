package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ModeracionDTO {
  private Long idModeracion;
  private Long idAdministrador;
  private Integer idEstadoModeracion;
  private Integer idMotivoRechazo;
  private String tipoObjeto;
  private Long idObjeto;
  private String observaciones;
  private LocalDateTime moderadoEn;
}
