
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.VisitanteDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.Visitante;
import com.example.Proyecto_Viajo.repository.VisitanteRepository;
import org.springframework.stereotype.Repository;

@Repository
public class VisitanteDAOImpl
    extends BaseDAOImpl<Visitante, Long>
    implements VisitanteDAO {

  public VisitanteDAOImpl(VisitanteRepository repository) {
    super(repository);
  }
}
