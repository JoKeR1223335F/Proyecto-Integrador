package com.example.Proyecto_Viajo.dao;

import com.example.Proyecto_Viajo.dao.shared.BaseDAO;
import com.example.Proyecto_Viajo.model.DestinoImagen;

import java.util.List;

public interface DestinoImagenDAO
    extends BaseDAO<DestinoImagen, Long> {

  List<DestinoImagen> listarPorDestino(Long idDestino);
}
