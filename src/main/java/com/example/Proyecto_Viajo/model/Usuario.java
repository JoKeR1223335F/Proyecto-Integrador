package com.example.Proyecto_Viajo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario")
  private Long idUsuario;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_rol", nullable = false)
  private Rol rol;

  @Column(name = "nombres", nullable = false, length = 60)
  private String nombres;

  @Column(name = "apellidos", nullable = false, length = 60)
  private String apellidos;

  @Column(name = "correo", nullable = false, unique = true, length = 100)
  private String correo;

  @Column(name = "usuario", nullable = false, unique = true, length = 40)
  private String usuario;

  @Column(name = "contrasena_hash", nullable = false, length = 255)
  private String contrasenaHash;

  @Column(name = "estado", nullable = false, length = 1)
  private String estado = "A";

  @Column(name = "fecha_registro", nullable = false, updatable = false)
  private LocalDateTime fechaRegistro;

  @PrePersist
  protected void alCrear() {
    if (fechaRegistro == null) {
      fechaRegistro = LocalDateTime.now();
    }
  }
}
