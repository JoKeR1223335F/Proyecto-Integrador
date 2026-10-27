package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "incidente_tecnico")
@Getter
@Setter
@NoArgsConstructor
public class IncidenteTecnico {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_incidente")
  private Long idIncidente;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_solicitud_registro")
  private SolicitudRegistro solicitudRegistro;

  @Column(name = "codigo", nullable = false, length = 100)
  private String codigo;

  @Column(name = "mensaje", nullable = false, columnDefinition = "TEXT")
  private String mensaje;

  @Column(name = "detalle_tecnico", columnDefinition = "TEXT")
  private String detalleTecnico;

  @Column(name = "resuelto", nullable = false)
  private Boolean resuelto = false;

  @Column(name = "ocurrido_en", nullable = false, updatable = false)
  private LocalDateTime ocurridoEn;

  @Column(name = "resuelto_en")
  private LocalDateTime resueltoEn;

  @PrePersist
  protected void alCrear() {
    if (ocurridoEn == null) {
      ocurridoEn = LocalDateTime.now();
    }

    if (resuelto == null) {
      resuelto = false;
    }
  }
}
