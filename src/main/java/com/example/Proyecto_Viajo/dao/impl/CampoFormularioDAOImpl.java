
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.CampoFormularioDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.CampoFormulario;
import com.example.Proyecto_Viajo.repository.CampoFormularioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CampoFormularioDAOImpl
    extends BaseDAOImpl<CampoFormulario, Long>
    implements CampoFormularioDAO {

  public CampoFormularioDAOImpl(CampoFormularioRepository repository) {
    super(repository);
  }
}
