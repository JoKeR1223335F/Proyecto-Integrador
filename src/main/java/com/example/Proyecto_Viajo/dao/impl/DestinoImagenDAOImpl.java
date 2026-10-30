
package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.DestinoImagenDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.DestinoImagen;
import com.example.Proyecto_Viajo.repository.DestinoImagenRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DestinoImagenDAOImpl
    extends BaseDAOImpl<DestinoImagen, Long>
    implements DestinoImagenDAO {

  private final DestinoImagenRepository imagenRepository;

  public DestinoImagenDAOImpl(
      DestinoImagenRepository repository) {
    super(repository);
    this.imagenRepository = repository;
  }

  @Override
  public List<DestinoImagen> listarPorDestino(Long idDestino) {
    return imagenRepository
        .findByDestino_IdDestinoOrderByOrdenAsc(idDestino);
  }
}
