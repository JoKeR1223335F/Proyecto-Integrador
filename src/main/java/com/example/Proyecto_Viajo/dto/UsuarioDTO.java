package com.example.Proyecto_Viajo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {
  private Long idUsuario;
  private Integer idRol;
  private String nombres;
  private String apellidos;
  private String correo;
  private String usuario;
  private String contrasenaHash;
  private String estado;
  private LocalDateTime fechaRegistro;
}
