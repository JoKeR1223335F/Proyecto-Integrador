
package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VisitanteDTO {
  private Long idVisitante;
  private Long idUsuario;
  private String codigoTipoTurismo;
  private String telefono;
  private String paisResidencia;
  private LocalDate fechaNacimiento;
  private LocalDateTime creadoEn;
}
