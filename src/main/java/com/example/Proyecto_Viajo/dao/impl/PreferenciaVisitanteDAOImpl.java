
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.PreferenciaVisitanteDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.PreferenciaVisitante;
import com.example.Proyecto_Viajo.repository.PreferenciaVisitanteRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PreferenciaVisitanteDAOImpl
    extends BaseDAOImpl<PreferenciaVisitante, Long>
    implements PreferenciaVisitanteDAO {

  public PreferenciaVisitanteDAOImpl(PreferenciaVisitanteRepository repository) {
    super(repository);
  }
}
