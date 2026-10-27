package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reporte_comentario")
@Getter
@Setter
@NoArgsConstructor
public class ReporteComentario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_reporte")
  private Long idReporte;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_comentario", nullable = false)
  private Comentario comentario;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_visitante", nullable = false)
  private Visitante visitante;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_motivo_rechazo", nullable = false)
  private MotivoRechazo motivoRechazo;

  @Column(name = "detalle", columnDefinition = "TEXT")
  private String detalle;

  @Column(name = "estado", nullable = false, length = 20)
  private String estado = "PENDIENTE";

  @Column(name = "creado_en", nullable = false, updatable = false)
  private LocalDateTime creadoEn;

  @PrePersist
  protected void alCrear() {
    if (creadoEn == null) {
      creadoEn = LocalDateTime.now();
    }
  }
}
