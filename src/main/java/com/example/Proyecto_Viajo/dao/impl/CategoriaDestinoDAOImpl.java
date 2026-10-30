
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.CategoriaDestinoDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.CategoriaDestino;
import com.example.Proyecto_Viajo.repository.CategoriaDestinoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaDestinoDAOImpl
    extends BaseDAOImpl<CategoriaDestino, String>
    implements CategoriaDestinoDAO {

  public CategoriaDestinoDAOImpl(CategoriaDestinoRepository repository) {
    super(repository);
  }
}
