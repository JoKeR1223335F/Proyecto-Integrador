package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "regla_validacion")
@Getter
@Setter
@NoArgsConstructor
public class ReglaValidacion {

  @Id
  @Column(name = "campo", nullable = false, length = 30)
  private String campo;

  @Column(name = "regla", nullable = false, length = 255)
  private String regla;

  @Column(name = "mensaje", nullable = false, length = 120)
  private String mensaje;
}
