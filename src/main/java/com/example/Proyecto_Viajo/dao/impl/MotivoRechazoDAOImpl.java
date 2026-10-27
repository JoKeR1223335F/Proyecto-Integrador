
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.MotivoRechazoDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.MotivoRechazo;
import com.example.Proyecto_Viajo.repository.MotivoRechazoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MotivoRechazoDAOImpl
    extends BaseDAOImpl<MotivoRechazo, Long>
    implements MotivoRechazoDAO {

  public MotivoRechazoDAOImpl(MotivoRechazoRepository repository) {
    super(repository);
  }
}
