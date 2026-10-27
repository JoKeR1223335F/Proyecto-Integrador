package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "publicacion")
@Getter
@Setter
@NoArgsConstructor
public class Publicacion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_publicacion")
  private Long idPublicacion;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_destino", nullable = false)
  private Destino destino;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_visitante", nullable = false)
  private Visitante visitante;

  @Column(name = "titulo", nullable = false, length = 200)
  private String titulo;

  @Column(name = "contenido", nullable = false, columnDefinition = "TEXT")
  private String contenido;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "estado_moderacion", nullable = false)
  private EstadoModeracion estadoModeracion;

  @Column(name = "publicada_en")
  private LocalDateTime publicadaEn;

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
