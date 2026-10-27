package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_turismo")
@Getter
@Setter
@NoArgsConstructor
public class TipoTurismo {

  @Id
  @Column(name = "codigo", nullable = false, length = 20)
  private String codigo;

  @Column(name = "descripcion", nullable = false, length = 80)
  private String descripcion;

  @Column(name = "orden", nullable = false)
  private Short orden = 0;

  @Column(name = "activo", nullable = false)
  private Boolean activo = true;
}
