
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.BitacoraProcesoDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.BitacoraProceso;
import com.example.Proyecto_Viajo.repository.BitacoraProcesoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BitacoraProcesoDAOImpl
    extends BaseDAOImpl<BitacoraProceso, Long>
    implements BitacoraProcesoDAO {

  public BitacoraProcesoDAOImpl(BitacoraProcesoRepository repository) {
    super(repository);
  }
}
