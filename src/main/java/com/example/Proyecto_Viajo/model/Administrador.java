package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "administrador")
@Getter
@Setter
@NoArgsConstructor
public class Administrador {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_administrador")
  private Long idAdministrador;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_rol", nullable = false)
  private Rol rol;

  @Column(name = "usuario", nullable = false, unique = true, length = 50)
  private String usuario;

  @Column(name = "correo", nullable = false, unique = true, length = 120)
  private String correo;

  @Column(name = "contrasena_hash", nullable = false, length = 255)
  private String contrasenaHash;

  @Column(name = "nombre_completo", nullable = false, length = 100)
  private String nombreCompleto;

  @Column(name = "estado", nullable = false, length = 20)
  private String estado = "ACTIVO";

  @Column(name = "intentos_fallidos", nullable = false)
  private Integer intentosFallidos = 0;

  @Column(name = "ultimo_acceso")
  private LocalDateTime ultimoAcceso;

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
