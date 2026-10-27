package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AdministradorDTO {

  private Long idAdministrador;
  private Integer idRol;

  private String usuario;
  private String correo;
  private String contrasenaHash;
  private String nombreCompleto;

  private String estado;
  private Integer intentosFallidos;

  private LocalDateTime ultimoAcceso;
  private LocalDateTime creadoEn;
  private LocalDateTime actualizadoEn;
}
