package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "motivo_rechazo")
@Getter
@Setter
@NoArgsConstructor
public class MotivoRechazo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_motivo")
  private Integer idMotivo;

  @Column(name = "nombre_motivo", nullable = false, unique = true, length = 50)
  private String nombreMotivo;

  @Column(name = "descripcion", length = 150)
  private String descripcion;

  @Column(name = "gravedad", nullable = false)
  private Short gravedad = 1;
}
