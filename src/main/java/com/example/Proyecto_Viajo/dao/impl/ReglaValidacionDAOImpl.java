
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.ReglaValidacionDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.ReglaValidacion;
import com.example.Proyecto_Viajo.repository.ReglaValidacionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ReglaValidacionDAOImpl
    extends BaseDAOImpl<ReglaValidacion, String>
    implements ReglaValidacionDAO {

  public ReglaValidacionDAOImpl(ReglaValidacionRepository repository) {
    super(repository);
  }
}
