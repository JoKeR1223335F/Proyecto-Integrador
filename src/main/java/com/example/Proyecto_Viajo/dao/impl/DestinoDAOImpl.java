
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.DestinoDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.Destino;
import com.example.Proyecto_Viajo.repository.DestinoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DestinoDAOImpl
    extends BaseDAOImpl<Destino, Long>
    implements DestinoDAO {

  public DestinoDAOImpl(DestinoRepository repository) {
    super(repository);
  }
}
