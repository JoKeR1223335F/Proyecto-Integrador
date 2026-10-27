
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.ReporteComentarioDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.ReporteComentario;
import com.example.Proyecto_Viajo.repository.ReporteComentarioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ReporteComentarioDAOImpl
    extends BaseDAOImpl<ReporteComentario, Long>
    implements ReporteComentarioDAO {

  public ReporteComentarioDAOImpl(ReporteComentarioRepository repository) {
    super(repository);
  }
}
