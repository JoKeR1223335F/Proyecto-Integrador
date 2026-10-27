
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.SolicitudOperacionDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.SolicitudOperacion;
import com.example.Proyecto_Viajo.repository.SolicitudOperacionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SolicitudOperacionDAOImpl
    extends BaseDAOImpl<SolicitudOperacion, Long>
    implements SolicitudOperacionDAO {

  public SolicitudOperacionDAOImpl(SolicitudOperacionRepository repository) {
    super(repository);
  }
}
