package com.example.Proyecto_Viajo.dao;

import com.example.Proyecto_Viajo.dao.shared.BaseDAO;
import com.example.Proyecto_Viajo.model.Usuario;

import java.util.Optional;

public interface UsuarioDAO extends BaseDAO<Usuario, Long> {

  Optional<Usuario> buscarPorCorreo(String correo);

  boolean existeCorreo(String correo);

  boolean existeUsuario(String usuario);
}
