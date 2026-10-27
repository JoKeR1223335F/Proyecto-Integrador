package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "destino")
@Getter
@Setter
@NoArgsConstructor
public class Destino {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_destino")
  private Long idDestino;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_categoria_destino", nullable = false)
  private CategoriaDestino categoriaDestino;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_visitante", nullable = false)
  private Visitante visitante;

  @Column(name = "nombre", nullable = false, length = 180)
  private String nombre;

  @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
  private String descripcion;

  @Column(name = "ubicacion", nullable = false, length = 250)
  private String ubicacion;

  @Column(name = "distrito", length = 100)
  private String distrito;

  @Column(name = "provincia", length = 100)
  private String provincia = "Ica";

  @Column(name = "departamento", length = 100)
  private String departamento = "Ica";

  @Column(name = "latitud", precision = 10, scale = 7)
  private BigDecimal latitud;

  @Column(name = "longitud", precision = 10, scale = 7)
  private BigDecimal longitud;

  @Column(name = "estado", nullable = false, length = 20)
  private String estado = "BORRADOR";

  @Column(name = "creado_en", nullable = false, updatable = false)
  private LocalDateTime creadoEn;

  @Column(name = "actualizado_en", nullable = false)
  private LocalDateTime actualizadoEn;

  @PrePersist
  protected void alCrear() {
    LocalDateTime ahora = LocalDateTime.now();

    if (creadoEn == null) {
      creadoEn = ahora;
    }

    if (actualizadoEn == null) {
      actualizadoEn = ahora;
    }
  }

  @PreUpdate
  protected void alActualizar() {
    actualizadoEn = LocalDateTime.now();
  }
}
