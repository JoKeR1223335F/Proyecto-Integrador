package com.example.Proyecto_Viajo.dao.shared.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Proyecto_Viajo.dao.shared.BaseDAO;

import java.util.List;
import java.util.Optional;

public abstract class BaseDAOImpl<T, ID>
    implements BaseDAO<T, ID> {

  protected final JpaRepository<T, ID> repository;

  protected BaseDAOImpl(JpaRepository<T, ID> repository) {
    this.repository = repository;
  }

  @Override
  public List<T> listarTodos() {
    return repository.findAll();
  }

  @Override
  public Optional<T> buscarPorId(ID id) {
    return repository.findById(id);
  }

  @Override
  public T guardar(T entidad) {
    return repository.save(entidad);
  }

  @Override
  public void eliminarPorId(ID id) {
    repository.deleteById(id);
  }

  @Override
  public boolean existePorId(ID id) {
    return repository.existsById(id);
  }
}
