package com.example.Proyecto_Viajo.repository;

import com.example.Proyecto_Viajo.model.SolicitudOperacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudOperacionRepository
    extends JpaRepository<SolicitudOperacion, Long> {

  List<SolicitudOperacion> findByDestino_IdDestino(
      Long idDestino);

  List<SolicitudOperacion> findByEstado(String estado);

  List<SolicitudOperacion> findByDestino_IdDestinoAndEstado(
      Long idDestino,
      String estado);
}
