package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "campo_formulario")
@Getter
@Setter
@NoArgsConstructor
public class CampoFormulario {

  @Id
  @Column(name = "codigo", nullable = false, length = 30)
  private String codigo;

  @Column(name = "etiqueta", nullable = false, length = 60)
  private String etiqueta;
}
