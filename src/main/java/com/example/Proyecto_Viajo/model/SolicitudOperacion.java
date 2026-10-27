package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitud_operacion")
@Getter
@Setter
@NoArgsConstructor
public class SolicitudOperacion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_solicitud_operacion")
  private Long idSolicitudOperacion;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_destino", nullable = false)
  private Destino destino;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_administrador")
  private Administrador administrador;

  @Column(name = "tipo_operacion", nullable = false, length = 30)
  private String tipoOperacion;

  @Column(name = "estado", nullable = false, length = 20)
  private String estado = "PENDIENTE";

  @Column(name = "motivo", columnDefinition = "TEXT")
  private String motivo;

  @Column(name = "observaciones", columnDefinition = "TEXT")
  private String observaciones;

  @Column(name = "solicitada_en", nullable = false, updatable = false)
  private LocalDateTime solicitadaEn;

  @Column(name = "resuelta_en")
  private LocalDateTime resueltaEn;

  @PrePersist
  protected void alCrear() {
    if (solicitadaEn == null) {
      solicitadaEn = LocalDateTime.now();
    }
  }
}
