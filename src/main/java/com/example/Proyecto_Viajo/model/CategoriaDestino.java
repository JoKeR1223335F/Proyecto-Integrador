package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria_destino")
@Getter
@Setter
@NoArgsConstructor
public class CategoriaDestino {

  @Id
  @Column(name = "codigo", nullable = false, length = 20)
  private String codigo;

  @Column(name = "descripcion", nullable = false, length = 80)
  private String descripcion;
}
