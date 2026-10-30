package com.example.Proyecto_Viajo.repository;

import com.example.Proyecto_Viajo.model.DestinoImagen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinoImagenRepository
    extends JpaRepository<DestinoImagen, Long> {

  List<DestinoImagen> findByDestino_IdDestinoOrderByOrdenAsc(
      Long idDestino);
}
