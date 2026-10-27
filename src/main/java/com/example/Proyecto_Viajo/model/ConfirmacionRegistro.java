package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "confirmacion_registro", uniqueConstraints = {
    @UniqueConstraint(name = "uq_confirmacion_registro_token", columnNames = "token_hash")
})
@Getter
@Setter
@NoArgsConstructor
public class ConfirmacionRegistro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_confirmacion")
  private Long idConfirmacion;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_solicitud_registro", nullable = false)
  private SolicitudRegistro solicitudRegistro;

  @Column(name = "token_hash", nullable = false, length = 255)
  private String tokenHash;

  @Column(name = "enviada_en", nullable = false, updatable = false)
  private LocalDateTime enviadaEn;

  @Column(name = "expira_en", nullable = false)
  private LocalDateTime expiraEn;

  @Column(name = "confirmada_en")
  private LocalDateTime confirmadaEn;

  @Column(name = "estado", nullable = false, length = 20)
  private String estado = "PENDIENTE";

  @PrePersist
  protected void alCrear() {
    if (enviadaEn == null) {
      enviadaEn = LocalDateTime.now();
    }

    if (estado == null) {
      estado = "PENDIENTE";
    }
  }
}
