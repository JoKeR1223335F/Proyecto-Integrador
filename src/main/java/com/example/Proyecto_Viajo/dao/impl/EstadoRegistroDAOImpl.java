
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.EstadoRegistroDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.EstadoRegistro;
import com.example.Proyecto_Viajo.repository.EstadoRegistroRepository;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoRegistroDAOImpl
    extends BaseDAOImpl<EstadoRegistro, String>
    implements EstadoRegistroDAO {

  public EstadoRegistroDAOImpl(EstadoRegistroRepository repository) {
    super(repository);
  }
}
