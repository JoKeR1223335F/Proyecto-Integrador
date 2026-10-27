
package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DestinoImagenDTO {
  private Long idDestinoImagen;
  private Long idDestino;
  private String urlImagen;
  private String textoAlternativo;
  private Boolean esPrincipal;
  private Integer orden;
  private LocalDateTime creadoEn;
}
