
package com.example.Proyecto_Viajo.repository;

import com.example.Proyecto_Viajo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository
    extends JpaRepository<Usuario, Long> {

  Optional<Usuario> findByCorreo(String correo);

  Optional<Usuario> findByUsuario(String usuario);

  boolean existsByCorreo(String correo);

  boolean existsByUsuario(String usuario);
}
