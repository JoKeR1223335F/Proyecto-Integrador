package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.model.EstadoModeracion;
import com.example.Proyecto_Viajo.repository.EstadoModeracionRepository;
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
class EstadoModeracionDAOImplTest {

  @Mock
  private EstadoModeracionRepository repository;

  @InjectMocks
  private EstadoModeracionDAOImpl dao;

  private EstadoModeracion crear() {
    EstadoModeracion entidad = new EstadoModeracion();
    entidad.setIdEstado(1);
    return entidad;
  }

  @Test
  void buscaPorIdCuandoExiste() {
    EstadoModeracion entidad = crear();
    when(repository.findById(1)).thenReturn(Optional.of(entidad));

    Optional<EstadoModeracion> resultado = dao.buscarPorId(1);

    assertTrue(resultado.isPresent());
    assertEquals(entidad, resultado.get());
  }

  @Test
  void devuelveVacioCuandoNoExiste() {
    when(repository.findById(1)).thenReturn(Optional.empty());

    assertTrue(dao.buscarPorId(1).isEmpty());
  }

  @Test
  void listaTodosLosRegistros() {
    when(repository.findAll()).thenReturn(List.of(crear()));

    assertEquals(1, dao.listarTodos().size());
  }

  @Test
  void guardaYDevuelveLaEntidad() {
    EstadoModeracion entidad = crear();
    when(repository.save(entidad)).thenReturn(entidad);

    assertEquals(entidad, dao.guardar(entidad));
  }

  @Test
  void indicaSiExistePorId() {
    when(repository.existsById(1)).thenReturn(true);

    assertTrue(dao.existePorId(1));
  }

  @Test
  void eliminaPorId() {
    dao.eliminarPorId(1);

    verify(repository).deleteById(1);
  }
}
