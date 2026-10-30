package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.dao.SolicitudOperacionDAO;
import com.example.Proyecto_Viajo.dao.shared.impl.BaseDAOImpl;
import com.example.Proyecto_Viajo.model.SolicitudOperacion;
import com.example.Proyecto_Viajo.repository.SolicitudOperacionRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SolicitudOperacionDAOImpl
    extends BaseDAOImpl<SolicitudOperacion, Long>
    implements SolicitudOperacionDAO {

  private final SolicitudOperacionRepository solicitudRepository;

  public SolicitudOperacionDAOImpl(
      SolicitudOperacionRepository repository) {
    super(repository);
    this.solicitudRepository = repository;
  }

  @Override
  public List<SolicitudOperacion> listarPorDestino(Long idDestino) {
    return solicitudRepository.findByDestino_IdDestino(idDestino);
  }

  @Override
  public List<SolicitudOperacion> listarPorEstado(String estado) {
    return solicitudRepository.findByEstado(estado);
  }

  @Override
  public List<SolicitudOperacion> listarPorDestinoYEstado(
      Long idDestino,
      String estado) {
    return solicitudRepository
        .findByDestino_IdDestinoAndEstado(idDestino, estado);
  }
}
