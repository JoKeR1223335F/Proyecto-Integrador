
package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DestinoDTO {
  private Long idDestino;
  private String codigoCategoriaDestino;
  private Long idVisitante;
  private String nombre;
  private String descripcion;
  private String ubicacion;
  private String distrito;
  private String provincia;
  private String departamento;
  private BigDecimal latitud;
  private BigDecimal longitud;
  private String estado;
  private LocalDateTime creadoEn;
  private LocalDateTime actualizadoEn;
}
