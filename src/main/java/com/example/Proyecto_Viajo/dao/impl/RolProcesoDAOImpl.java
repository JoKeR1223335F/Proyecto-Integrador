
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.RolProcesoDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.RolProceso;
import com.example.Proyecto_Viajo.repository.RolProcesoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RolProcesoDAOImpl
    extends BaseDAOImpl<RolProceso, Long>
    implements RolProcesoDAO {

  public RolProcesoDAOImpl(RolProcesoRepository repository) {
    super(repository);
  }
}
