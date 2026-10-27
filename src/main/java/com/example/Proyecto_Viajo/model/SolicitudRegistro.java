package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tools.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitud_registro")
@Getter
@Setter
@NoArgsConstructor
public class SolicitudRegistro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_solicitud_registro")
  private Long idSolicitudRegistro;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario")
  private Usuario usuario;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_estado_registro", nullable = false)
  private EstadoRegistro estadoRegistro;

  @Column(name = "correo", nullable = false, length = 254)
  private String correo;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "datos_enviados", columnDefinition = "jsonb")
  private JsonNode datosEnviados;

  @Column(name = "intentos", nullable = false)
  private Integer intentos = 0;

  @Column(name = "fecha_creacion", nullable = false, updatable = false)
  private LocalDateTime fechaCreacion;

  @Column(name = "fecha_ultima_actividad", nullable = false)
  private LocalDateTime fechaUltimaActividad;

  @Column(name = "fecha_finalizacion")
  private LocalDateTime fechaFinalizacion;

  @Column(name = "token_confirmacion_hash", length = 255)
  private String tokenConfirmacionHash;

  @Column(name = "expira_en")
  private LocalDateTime expiraEn;

  @Column(name = "observaciones", columnDefinition = "TEXT")
  private String observaciones;

  @PrePersist
  protected void alCrear() {
    LocalDateTime ahora = LocalDateTime.now();

    if (fechaCreacion == null) {
      fechaCreacion = ahora;
    }

    if (fechaUltimaActividad == null) {
      fechaUltimaActividad = ahora;
    }

    if (intentos == null) {
      intentos = 0;
    }
  }
}
