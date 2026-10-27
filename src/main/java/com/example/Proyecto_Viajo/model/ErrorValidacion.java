package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "error_validacion")
@Getter
@Setter
@NoArgsConstructor
public class ErrorValidacion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_error_validacion")
  private Long idErrorValidacion;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_solicitud_registro", nullable = false)
  private SolicitudRegistro solicitudRegistro;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_campo_formulario")
  private CampoFormulario campoFormulario;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_regla_validacion")
  private ReglaValidacion reglaValidacion;

  @Column(name = "codigo_error", nullable = false, length = 80)
  private String codigoError;

  @Column(name = "mensaje", nullable = false, columnDefinition = "TEXT")
  private String mensaje;

  @Column(name = "valor_recibido", columnDefinition = "TEXT")
  private String valorRecibido;

  @Column(name = "creado_en", nullable = false, updatable = false)
  private LocalDateTime creadoEn;

  @PrePersist
  protected void alCrear() {
    if (creadoEn == null) {
      creadoEn = LocalDateTime.now();
    }
  }
}
