
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.PublicacionDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.Publicacion;
import com.example.Proyecto_Viajo.repository.PublicacionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PublicacionDAOImpl
    extends BaseDAOImpl<Publicacion, Long>
    implements PublicacionDAO {

  public PublicacionDAOImpl(PublicacionRepository repository) {
    super(repository);
  }
}
