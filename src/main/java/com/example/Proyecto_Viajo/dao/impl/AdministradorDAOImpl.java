
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.AdministradorDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.Administrador;
import com.example.Proyecto_Viajo.repository.AdministradorRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AdministradorDAOImpl
    extends BaseDAOImpl<Administrador, Long>
    implements AdministradorDAO {

  public AdministradorDAOImpl(AdministradorRepository repository) {
    super(repository);
  }
}
