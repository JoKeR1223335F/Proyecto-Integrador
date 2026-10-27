
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.ErrorValidacionDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.ErrorValidacion;
import com.example.Proyecto_Viajo.repository.ErrorValidacionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ErrorValidacionDAOImpl
    extends BaseDAOImpl<ErrorValidacion, Long>
    implements ErrorValidacionDAO {

  public ErrorValidacionDAOImpl(ErrorValidacionRepository repository) {
    super(repository);
  }
}
