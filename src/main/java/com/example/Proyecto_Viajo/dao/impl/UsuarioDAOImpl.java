
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.UsuarioDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.Usuario;
import com.example.Proyecto_Viajo.repository.UsuarioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImpl
    extends BaseDAOImpl<Usuario, Long>
    implements UsuarioDAO {

  public UsuarioDAOImpl(UsuarioRepository repository) {
    super(repository);
  }
}
