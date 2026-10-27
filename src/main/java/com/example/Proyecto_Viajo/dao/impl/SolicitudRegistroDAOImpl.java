
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.SolicitudRegistroDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.SolicitudRegistro;
import com.example.Proyecto_Viajo.repository.SolicitudRegistroRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SolicitudRegistroDAOImpl
    extends BaseDAOImpl<SolicitudRegistro, Long>
    implements SolicitudRegistroDAO {

  public SolicitudRegistroDAOImpl(SolicitudRegistroRepository repository) {
    super(repository);
  }
}
