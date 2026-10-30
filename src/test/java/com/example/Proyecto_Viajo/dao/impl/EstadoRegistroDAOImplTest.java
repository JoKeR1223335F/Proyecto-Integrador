package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.model.EstadoRegistro;
import com.example.Proyecto_Viajo.repository.EstadoRegistroRepository;
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
class EstadoRegistroDAOImplTest {

  @Mock
  private EstadoRegistroRepository repository;

  @InjectMocks
  private EstadoRegistroDAOImpl dao;

  private EstadoRegistro crear() {
    EstadoRegistro entidad = new EstadoRegistro();
    entidad.setCodigo("EN_CAPTURA");
    return entidad;
  }

  @Test
  void buscaPorIdCuandoExiste() {
    EstadoRegistro entidad = crear();
    when(repository.findById("EN_CAPTURA")).thenReturn(Optional.of(entidad));

    Optional<EstadoRegistro> resultado = dao.buscarPorId("EN_CAPTURA");

    assertTrue(resultado.isPresent());
    assertEquals(entidad, resultado.get());
  }

  @Test
  void devuelveVacioCuandoNoExiste() {
    when(repository.findById("EN_CAPTURA")).thenReturn(Optional.empty());

    assertTrue(dao.buscarPorId("EN_CAPTURA").isEmpty());
  }

  @Test
  void listaTodosLosRegistros() {
    when(repository.findAll()).thenReturn(List.of(crear()));

    assertEquals(1, dao.listarTodos().size());
  }

  @Test
  void guardaYDevuelveLaEntidad() {
    EstadoRegistro entidad = crear();
    when(repository.save(entidad)).thenReturn(entidad);

    assertEquals(entidad, dao.guardar(entidad));
  }

  @Test
  void indicaSiExistePorId() {
    when(repository.existsById("EN_CAPTURA")).thenReturn(true);

    assertTrue(dao.existePorId("EN_CAPTURA"));
  }

  @Test
  void eliminaPorId() {
    dao.eliminarPorId("EN_CAPTURA");

    verify(repository).deleteById("EN_CAPTURA");
  }
}
