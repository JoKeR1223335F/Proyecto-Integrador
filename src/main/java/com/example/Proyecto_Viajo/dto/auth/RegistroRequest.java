package com.example.Proyecto_Viajo.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistroRequest(

    @NotNull(message = "El rol es obligatorio") Integer idRol,

    @NotBlank(message = "Los nombres son obligatorios") @Size(max = 60, message = "Los nombres no pueden superar 60 caracteres") String nombres,

    @NotBlank(message = "Los apellidos son obligatorios") @Size(max = 60, message = "Los apellidos no pueden superar 60 caracteres") String apellidos,

    @NotBlank(message = "El correo es obligatorio") @Email(message = "El correo no tiene un formato válido") @Size(max = 100) String correo,

    @NotBlank(message = "El nombre de usuario es obligatorio") @Size(max = 40, message = "El usuario no puede superar 40 caracteres") String usuario,

    @NotBlank(message = "La contraseña es obligatoria") @Size(min = 8, max = 72, message = "La contraseña debe tener entre 8 y 72 caracteres") String contrasena

) {
}
