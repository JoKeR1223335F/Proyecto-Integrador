package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estado_registro")
@Getter
@Setter
@NoArgsConstructor
public class EstadoRegistro {

  @Id
  @Column(name = "codigo", nullable = false, length = 30)
  private String codigo;

  @Column(name = "significado", nullable = false, length = 255)
  private String significado;

  @Column(name = "accion", nullable = false, length = 255)
  private String accion;

  @Column(name = "es_final", nullable = false)
  private Boolean esFinal = false;
}
