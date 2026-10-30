package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.model.CategoriaDestino;
import com.example.Proyecto_Viajo.repository.CategoriaDestinoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaDestinoDAOImplTest {

  @Mock
  private CategoriaDestinoRepository repository;

  @InjectMocks
  private CategoriaDestinoDAOImpl dao;

  private CategoriaDestino crear() {
    CategoriaDestino entidad = new CategoriaDestino();
    entidad.setCodigo("PLAYA");
    return entidad;
  }

  @Test
  void buscaPorIdCuandoExiste() {
    CategoriaDestino entidad = crear();
    when(repository.findById("PLAYA")).thenReturn(Optional.of(entidad));

    Optional<CategoriaDestino> resultado = dao.buscarPorId("PLAYA");

    assertTrue(resultado.isPresent());
    assertEquals(entidad, resultado.get());
  }

  @Test
  void devuelveVacioCuandoNoExiste() {
    when(repository.findById("PLAYA")).thenReturn(Optional.empty());

    assertTrue(dao.buscarPorId("PLAYA").isEmpty());
  }

  @Test
  void listaTodosLosRegistros() {
    when(repository.findAll()).thenReturn(List.of(crear()));

    assertEquals(1, dao.listarTodos().size());
  }

  @Test
  void guardaYDevuelveLaEntidad() {
    CategoriaDestino entidad = crear();
    when(repository.save(entidad)).thenReturn(entidad);

    assertEquals(entidad, dao.guardar(entidad));
  }

  @Test
  void indicaSiExistePorId() {
    when(repository.existsById("PLAYA")).thenReturn(true);

    assertTrue(dao.existePorId("PLAYA"));
  }

  @Test
  void eliminaPorId() {
    dao.eliminarPorId("PLAYA");

    verify(repository).deleteById("PLAYA");
  }
}
