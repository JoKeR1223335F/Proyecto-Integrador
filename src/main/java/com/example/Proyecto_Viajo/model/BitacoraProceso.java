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
@Table(name = "bitacora_proceso")
@Getter
@Setter
@NoArgsConstructor
public class BitacoraProceso {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_bitacora")
  private Long idBitacora;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_solicitud_registro")
  private SolicitudRegistro solicitudRegistro;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario")
  private Usuario usuario;

  @Column(name = "proceso", nullable = false, length = 100)
  private String proceso;

  @Column(name = "evento", nullable = false, length = 100)
  private String evento;

  @Column(name = "nivel", nullable = false, length = 20)
  private String nivel = "INFO";

  @Column(name = "descripcion", columnDefinition = "TEXT")
  private String descripcion;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "metadatos", columnDefinition = "jsonb")
  private JsonNode metadatos;

  @Column(name = "creado_en", nullable = false, updatable = false)
  private LocalDateTime creadoEn;

  @PrePersist
  protected void alCrear() {
    if (creadoEn == null) {
      creadoEn = LocalDateTime.now();
    }

    if (nivel == null) {
      nivel = "INFO";
    }
  }
}
