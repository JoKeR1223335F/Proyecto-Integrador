
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.ComentarioDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.Comentario;
import com.example.Proyecto_Viajo.repository.ComentarioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ComentarioDAOImpl
    extends BaseDAOImpl<Comentario, Long>
    implements ComentarioDAO {

  public ComentarioDAOImpl(ComentarioRepository repository) {
    super(repository);
  }
}
