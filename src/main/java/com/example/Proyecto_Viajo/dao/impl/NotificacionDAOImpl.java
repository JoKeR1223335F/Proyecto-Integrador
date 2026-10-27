
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.NotificacionDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.Notificacion;
import com.example.Proyecto_Viajo.repository.NotificacionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class NotificacionDAOImpl
    extends BaseDAOImpl<Notificacion, Long>
    implements NotificacionDAO {

  public NotificacionDAOImpl(NotificacionRepository repository) {
    super(repository);
  }
}
