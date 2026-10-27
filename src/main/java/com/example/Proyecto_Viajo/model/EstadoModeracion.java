package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estado_moderacion")
@Getter
@Setter
@NoArgsConstructor
public class EstadoModeracion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_estado")
  private Integer idEstado;

  @Column(name = "nombre_estado", nullable = false, unique = true, length = 20)
  private String nombreEstado;

  @Column(name = "descripcion", length = 150)
  private String descripcion;
}
