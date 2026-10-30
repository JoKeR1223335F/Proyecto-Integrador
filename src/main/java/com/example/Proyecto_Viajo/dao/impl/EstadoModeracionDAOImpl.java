
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.EstadoModeracionDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.EstadoModeracion;
import com.example.Proyecto_Viajo.repository.EstadoModeracionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoModeracionDAOImpl
    extends BaseDAOImpl<EstadoModeracion, Integer>
    implements EstadoModeracionDAO {

  public EstadoModeracionDAOImpl(EstadoModeracionRepository repository) {
    super(repository);
  }
}
