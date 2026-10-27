package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificacion")
@Getter
@Setter
@NoArgsConstructor
public class Notificacion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_notificacion")
  private Long idNotificacion;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_visitante")
  private Visitante visitante;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_administrador")
  private Administrador administrador;

  @Column(name = "titulo", nullable = false, length = 180)
  private String titulo;

  @Column(name = "mensaje", nullable = false, columnDefinition = "TEXT")
  private String mensaje;

  @Column(name = "tipo", nullable = false, length = 40)
  private String tipo;

  @Column(name = "leida", nullable = false)
  private Boolean leida = false;

  @Column(name = "creado_en", nullable = false, updatable = false)
  private LocalDateTime creadoEn;

  @Column(name = "leida_en")
  private LocalDateTime leidaEn;

  @PrePersist
  protected void alCrear() {
    if (creadoEn == null) {
      creadoEn = LocalDateTime.now();
    }

    if (leida == null) {
      leida = false;
    }
  }
}
