
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.RolDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.Rol;
import com.example.Proyecto_Viajo.repository.RolRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RolDAOImpl
    extends BaseDAOImpl<Rol, Long>
    implements RolDAO {

  public RolDAOImpl(RolRepository repository) {
    super(repository);
  }
}
