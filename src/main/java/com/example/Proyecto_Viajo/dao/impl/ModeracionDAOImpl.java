
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.ModeracionDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.Moderacion;
import com.example.Proyecto_Viajo.repository.ModeracionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ModeracionDAOImpl
    extends BaseDAOImpl<Moderacion, Long>
    implements ModeracionDAO {

  public ModeracionDAOImpl(ModeracionRepository repository) {
    super(repository);
  }
}
