package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentario")
@Getter
@Setter
@NoArgsConstructor
public class Comentario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_comentario")
  private Long idComentario;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_publicacion", nullable = false)
  private Publicacion publicacion;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_visitante", nullable = false)
  private Visitante visitante;

  @Column(name = "contenido", nullable = false, columnDefinition = "TEXT")
  private String contenido;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "estado_moderacion", nullable = false)
  private EstadoModeracion estadoModeracion;

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
