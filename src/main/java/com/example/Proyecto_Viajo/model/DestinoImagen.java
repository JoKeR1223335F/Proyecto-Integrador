package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "destino_imagen")
@Getter
@Setter
@NoArgsConstructor
public class DestinoImagen {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_destino_imagen")
  private Long idDestinoImagen;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_destino", nullable = false)
  private Destino destino;

  @Column(name = "url_imagen", nullable = false, length = 1000)
  private String urlImagen;

  @Column(name = "texto_alternativo", length = 250)
  private String textoAlternativo;

  @Column(name = "es_principal", nullable = false)
  private Boolean esPrincipal = false;

  @Column(name = "orden", nullable = false)
  private Integer orden = 0;

  @Column(name = "creado_en", nullable = false, updatable = false)
  private LocalDateTime creadoEn;

  @PrePersist
  protected void alCrear() {
    if (creadoEn == null) {
      creadoEn = LocalDateTime.now();
    }
  }
}
