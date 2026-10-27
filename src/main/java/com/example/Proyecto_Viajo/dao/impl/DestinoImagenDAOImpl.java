
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.DestinoImagenDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.DestinoImagen;
import com.example.Proyecto_Viajo.repository.DestinoImagenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DestinoImagenDAOImpl
    extends BaseDAOImpl<DestinoImagen, Long>
    implements DestinoImagenDAO {

  public DestinoImagenDAOImpl(DestinoImagenRepository repository) {
    super(repository);
  }
}
