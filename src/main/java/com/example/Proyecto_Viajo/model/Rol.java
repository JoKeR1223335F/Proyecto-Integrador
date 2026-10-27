package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol")
@Getter
@Setter
@NoArgsConstructor
public class Rol {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_rol")
  private Integer idRol;

  @Column(name = "nombre_rol", nullable = false, unique = true, length = 30)
  private String nombreRol;

  @Column(name = "descripcion", length = 150)
  private String descripcion;
}
