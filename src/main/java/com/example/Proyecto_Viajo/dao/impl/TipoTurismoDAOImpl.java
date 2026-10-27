
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.TipoTurismoDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.TipoTurismo;
import com.example.Proyecto_Viajo.repository.TipoTurismoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TipoTurismoDAOImpl
    extends BaseDAOImpl<TipoTurismo, Long>
    implements TipoTurismoDAO {

  public TipoTurismoDAOImpl(TipoTurismoRepository repository) {
    super(repository);
  }
}
