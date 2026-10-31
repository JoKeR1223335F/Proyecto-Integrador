package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.UsuarioDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.Usuario;
import com.example.Proyecto_Viajo.repository.UsuarioRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioDAOImpl
    extends BaseDAOImpl<Usuario, Long>
    implements UsuarioDAO {

  private final UsuarioRepository usuarioRepository;

  public UsuarioDAOImpl(UsuarioRepository repository) {
    super(repository);
    this.usuarioRepository = repository;
  }

  @Override
  public Optional<Usuario> buscarPorCorreo(String correo) {
    return usuarioRepository.findByCorreo(correo);
  }

  @Override
  public boolean existeCorreo(String correo) {
    return usuarioRepository.existsByCorreo(correo);
  }

  @Override
  public boolean existeUsuario(String usuario) {
    return usuarioRepository.existsByUsuario(usuario);
  }
}
