
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.ConfirmacionRegistroDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.ConfirmacionRegistro;
import com.example.Proyecto_Viajo.repository.ConfirmacionRegistroRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ConfirmacionRegistroDAOImpl
    extends BaseDAOImpl<ConfirmacionRegistro, Long>
    implements ConfirmacionRegistroDAO {

  public ConfirmacionRegistroDAOImpl(ConfirmacionRegistroRepository repository) {
    super(repository);
  }
}
