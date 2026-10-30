package com.example.Proyecto_Viajo.dao;

import com.example.Proyecto_Viajo.dao.shared.BaseDAO;
import com.example.Proyecto_Viajo.model.SolicitudOperacion;

import java.util.List;

public interface SolicitudOperacionDAO
    extends BaseDAO<SolicitudOperacion, Long> {

  List<SolicitudOperacion> listarPorDestino(Long idDestino);

  List<SolicitudOperacion> listarPorEstado(String estado);

  List<SolicitudOperacion> listarPorDestinoYEstado(
      Long idDestino,
      String estado);
}
