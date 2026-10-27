package com.example.Proyecto_Viajo.dao.shared;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T, ID> {
  List<T> listarTodos();

  Optional<T> buscarPorId(ID id);

  T guardar(T entidad);

  void eliminarPorId(ID id);

  boolean existePorId(ID id);
}
