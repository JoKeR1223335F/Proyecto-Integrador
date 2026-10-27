
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.IncidenteTecnicoDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.IncidenteTecnico;
import com.example.Proyecto_Viajo.repository.IncidenteTecnicoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class IncidenteTecnicoDAOImpl
    extends BaseDAOImpl<IncidenteTecnico, Long>
    implements IncidenteTecnicoDAO {

  public IncidenteTecnicoDAOImpl(IncidenteTecnicoRepository repository) {
    super(repository);
  }
}
