package com.example.Proyecto_Viajo.dao.impl;

import com.example.Proyecto_Viajo.model.MotivoRechazo;
import com.example.Proyecto_Viajo.repository.MotivoRechazoRepository;
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
class MotivoRechazoDAOImplTest {

  @Mock
  private MotivoRechazoRepository repository;

  @InjectMocks
  private MotivoRechazoDAOImpl dao;

  private MotivoRechazo crear() {
    MotivoRechazo entidad = new MotivoRechazo();
    entidad.setIdMotivo(1);
    return entidad;
  }

  @Test
  void buscaPorIdCuandoExiste() {
    MotivoRechazo entidad = crear();
    when(repository.findById(1)).thenReturn(Optional.of(entidad));

    Optional<MotivoRechazo> resultado = dao.buscarPorId(1);

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
    MotivoRechazo entidad = crear();
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
