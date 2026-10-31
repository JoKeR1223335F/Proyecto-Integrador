package com.example.Proyecto_Viajo.service;

import com.example.Proyecto_Viajo.dao.UsuarioDAO;
import com.example.Proyecto_Viajo.dto.auth.RegistroRequest;
import com.example.Proyecto_Viajo.dto.auth.RegistroResponse;
import com.example.Proyecto_Viajo.model.Rol;
import com.example.Proyecto_Viajo.model.Usuario;
import com.example.Proyecto_Viajo.repository.RolRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.HttpStatus;

@Service
public class UsuarioService {

  private static final Integer ROL_VISITANTE = 2;

  private final UsuarioDAO usuarioDAO;
  private final PasswordEncoder passwordEncoder;
  private final RolRepository rolRepository;

  public UsuarioService(
      UsuarioDAO usuarioDAO,
      PasswordEncoder passwordEncoder,
      RolRepository rolRepository) {

    this.usuarioDAO = usuarioDAO;
    this.passwordEncoder = passwordEncoder;
    this.rolRepository = rolRepository;
  }

  // ==========================================
  // AUTENTICAR USUARIO
  // ==========================================

  @Transactional(readOnly = true)
  public Long autenticar(String correo, String contrasena) {

    if (correo == null || correo.isBlank() ||
        contrasena == null || contrasena.isBlank()) {

      throw new IllegalArgumentException(
          "Correo y contraseña son obligatorios");
    }

    Usuario usuario = usuarioDAO
        .buscarPorCorreo(correo.trim().toLowerCase())
        .orElseThrow(() -> new IllegalArgumentException(
            "Credenciales incorrectas"));

    if (!"A".equals(usuario.getEstado())) {
      throw new IllegalArgumentException(
          "La cuenta se encuentra inactiva");
    }

    if (!passwordEncoder.matches(
        contrasena,
        usuario.getContrasenaHash())) {

      throw new IllegalArgumentException(
          "Credenciales incorrectas");
    }

    return usuario.getIdUsuario();
  }

  // ==========================================
  // REGISTRAR USUARIO
  // ==========================================

  @Transactional
  public RegistroResponse registrar(RegistroRequest request) {

    // VALIDAR CAMPOS OBLIGATORIOS

    if (request == null ||
        request.nombres() == null ||
        request.apellidos() == null ||
        request.correo() == null ||
        request.usuario() == null ||
        request.contrasena() == null) {

      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "Todos los campos son obligatorios");
    }

    String nombres = request.nombres().trim();
    String apellidos = request.apellidos().trim();
    String correo = request.correo().trim().toLowerCase();
    String nombreUsuario = request.usuario().trim();
    String contrasena = request.contrasena();

    // VALIDAR LONGITUDES

    if (nombres.isBlank() || nombres.length() > 60 ||
        apellidos.isBlank() || apellidos.length() > 60 ||
        correo.length() > 100 ||
        nombreUsuario.isBlank() ||
        nombreUsuario.length() > 40) {

      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "Los datos ingresados no son válidos");
    }

    // VALIDAR CORREO

    if (!correo.matches(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "El correo electrónico no es válido");
    }

    // VALIDAR CONTRASEÑA

    if (contrasena.length() < 8 ||
        contrasena.length() > 72) {

      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "La contraseña debe tener entre 8 y 72 caracteres");
    }

    // VALIDAR CORREO EXISTENTE

    if (usuarioDAO.existeCorreo(correo)) {

      throw new ResponseStatusException(
          HttpStatus.CONFLICT,
          "El correo electrónico ya está registrado");
    }

    // VALIDAR NOMBRE DE USUARIO EXISTENTE

    if (usuarioDAO.existeUsuario(nombreUsuario)) {

      throw new ResponseStatusException(
          HttpStatus.CONFLICT,
          "El nombre de usuario ya está ocupado");
    }

    // BUSCAR ROL VISITANTE

    Rol rol = rolRepository.findById(ROL_VISITANTE)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "No se encontró el rol VISITANTE"));

    // CONSTRUIR USUARIO

    Usuario usuario = new Usuario();

    usuario.setRol(rol);
    usuario.setNombres(nombres);
    usuario.setApellidos(apellidos);
    usuario.setCorreo(correo);
    usuario.setUsuario(nombreUsuario);

    // ENCRIPTAR CONTRASEÑA CON BCRYPT

    usuario.setContrasenaHash(
        passwordEncoder.encode(contrasena));

    usuario.setEstado("A");

    // GUARDAR USUARIO

    Usuario guardado = usuarioDAO.guardar(usuario);

    return new RegistroResponse(
        guardado.getIdUsuario(),
        "Usuario registrado correctamente");
  }
}
