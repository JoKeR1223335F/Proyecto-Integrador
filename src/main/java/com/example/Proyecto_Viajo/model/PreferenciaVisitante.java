package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "preferencia_visitante")
@Getter
@Setter
@NoArgsConstructor
public class PreferenciaVisitante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_preferencia")
  private Long idPreferencia;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_visitante", nullable = false)
  private Visitante visitante;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_tipo_turismo", nullable = false)
  private TipoTurismo tipoTurismo;

  @Column(name = "fecha_inicio", nullable = false, updatable = false)
  private LocalDateTime fechaInicio;

  @Column(name = "fecha_fin")
  private LocalDateTime fechaFin;

  @Column(name = "activa", nullable = false)
  private Boolean activa = true;

  @PrePersist
  protected void alCrear() {
    if (fechaInicio == null) {
      fechaInicio = LocalDateTime.now();
    }
  }
}
