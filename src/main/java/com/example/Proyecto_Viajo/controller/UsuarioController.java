
package com.example.Proyecto_Viajo.controller;

import com.example.Proyecto_Viajo.controller.shared.CrudController;
import com.example.Proyecto_Viajo.dto.auth.LoginRequest;
import com.example.Proyecto_Viajo.dto.auth.LoginResponse;
import com.example.Proyecto_Viajo.dto.auth.RegistroRequest;
import com.example.Proyecto_Viajo.dto.auth.RegistroResponse;
import com.example.Proyecto_Viajo.model.Usuario;
import com.example.Proyecto_Viajo.repository.UsuarioRepository;
import com.example.Proyecto_Viajo.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController
    extends CrudController<Usuario, Long> {

  private final UsuarioService usuarioService;

  public UsuarioController(
      UsuarioRepository repository,
      UsuarioService usuarioService) {

    super(repository);
    this.usuarioService = usuarioService;
  }

  // ==========================================
  // AUTENTICAR USUARIO
  // ==========================================

  @PostMapping("/autenticar")
  public ResponseEntity<?> autenticar(
      @RequestBody LoginRequest request,
      HttpSession session) {

    try {

      if (request == null ||
          request.correo() == null ||
          request.contrasena() == null ||
          request.correo().isBlank() ||
          request.contrasena().isBlank()) {

        return ResponseEntity.badRequest()
            .body("Correo y contraseña son obligatorios");
      }

      Long idUsuario = usuarioService.autenticar(
          request.correo().trim(),
          request.contrasena());

      // Guardar identificador en la sesión
      session.setAttribute("usuarioId", idUsuario);

      return ResponseEntity.ok(
          new LoginResponse(idUsuario));

    } catch (IllegalArgumentException e) {

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(e.getMessage());
    }
  }

  // ==========================================
  // REGISTRAR USUARIO
  // ==========================================

  @PostMapping("/registro")
  public ResponseEntity<?> registrar(
      @RequestBody RegistroRequest request) {

    try {

      if (request == null) {
        return ResponseEntity.badRequest()
            .body("Los datos del registro son obligatorios");
      }

      RegistroResponse respuesta = usuarioService.registrar(request);

      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(respuesta);

    } catch (IllegalArgumentException e) {

      return ResponseEntity.badRequest()
          .body(e.getMessage());

    } catch (Exception e) {

      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("No fue posible registrar el usuario");
    }
  }

  // ==========================================
  // CERRAR SESIÓN
  // ==========================================

  @PostMapping("/cerrar-sesion")
  public ResponseEntity<?> cerrarSesion(
      HttpSession session) {

    session.invalidate();

    return ResponseEntity.ok(
        "Sesión cerrada correctamente");
  }
}
