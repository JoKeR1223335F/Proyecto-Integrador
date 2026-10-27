package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "moderacion")
@Getter
@Setter
@NoArgsConstructor
public class Moderacion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_moderacion")
  private Long idModeracion;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_administrador", nullable = false)
  private Administrador administrador;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_estado_moderacion", nullable = false)
  private EstadoModeracion estadoModeracion;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_motivo_rechazo")
  private MotivoRechazo motivoRechazo;

  @Column(name = "tipo_objeto", nullable = false, length = 30)
  private String tipoObjeto;

  @Column(name = "id_objeto", nullable = false)
  private Long idObjeto;

  @Column(name = "observaciones", columnDefinition = "TEXT")
  private String observaciones;

  @Column(name = "moderado_en", nullable = false, updatable = false)
  private LocalDateTime moderadoEn;

  @PrePersist
  protected void alCrear() {
    if (moderadoEn == null) {
      moderadoEn = LocalDateTime.now();
    }
  }
}
