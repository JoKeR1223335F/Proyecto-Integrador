package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol_proceso")
@Getter
@Setter
@NoArgsConstructor
public class RolProceso {

  @Id
  @Column(name = "codigo", nullable = false, length = 20)
  private String codigo;

  @Column(name = "tipo", nullable = false, length = 30)
  private String tipo;

  @Column(name = "responsabilidad", nullable = false, length = 400)
  private String responsabilidad;
}
