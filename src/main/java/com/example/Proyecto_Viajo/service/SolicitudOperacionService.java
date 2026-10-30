package com.example.Proyecto_Viajo.service;

import com.example.Proyecto_Viajo.dao.SolicitudOperacionDAO;
import com.example.Proyecto_Viajo.dao.DestinoDAO;
import com.example.Proyecto_Viajo.dao.AdministradorDAO;

import com.example.Proyecto_Viajo.model.SolicitudOperacion;
import com.example.Proyecto_Viajo.model.Destino;
import com.example.Proyecto_Viajo.model.Administrador;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class SolicitudOperacionService {

  private final SolicitudOperacionDAO solicitudDAO;
  private final DestinoDAO destinoDAO;
  private final AdministradorDAO administradorDAO;

  private static final Set<String> TIPOS_VALIDOS = Set.of(
      "PUBLICAR",
      "EDITAR",
      "ELIMINAR");

  private static final Set<String> ESTADOS_VALIDOS = Set.of(
      "PENDIENTE",
      "APROBADA",
      "RECHAZADA");

  public SolicitudOperacionService(
      SolicitudOperacionDAO solicitudDAO,
      DestinoDAO destinoDAO,
      AdministradorDAO administradorDAO) {
    this.solicitudDAO = solicitudDAO;
    this.destinoDAO = destinoDAO;
    this.administradorDAO = administradorDAO;
  }

  // LISTAR TODAS
  @Transactional(readOnly = true)
  public List<SolicitudOperacion> listarTodos() {
    return solicitudDAO.listarTodos();
  }

  // BUSCAR POR ID
  @Transactional(readOnly = true)
  public SolicitudOperacion buscarPorId(Long id) {
    return solicitudDAO.buscarPorId(id)
        .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada."));
  }

  // REGISTRAR SOLICITUD
  @Transactional
  public SolicitudOperacion crear(
      Long idDestino,
      String tipoOperacion,
      String motivo) {

    if (tipoOperacion == null ||
        !TIPOS_VALIDOS.contains(tipoOperacion)) {
      throw new IllegalArgumentException(
          "Tipo de operación inválido.");
    }

    Destino destino = destinoDAO.buscarPorId(idDestino)
        .orElseThrow(() -> new IllegalArgumentException("Destino no encontrado."));

    SolicitudOperacion solicitud = new SolicitudOperacion();

    solicitud.setDestino(destino);
    solicitud.setTipoOperacion(tipoOperacion);
    solicitud.setEstado("PENDIENTE");
    solicitud.setMotivo(motivo);

    return solicitudDAO.guardar(solicitud);
  }

  // ACTUALIZAR ESTADO DE SOLICITUD
  @Transactional
  public SolicitudOperacion resolver(
      Long id,
      Long idAdministrador,
      String estado,
      String observaciones) {

    if (estado == null ||
        !Set.of("APROBADA", "RECHAZADA").contains(estado)) {
      throw new IllegalArgumentException(
          "El estado debe ser APROBADA o RECHAZADA.");
    }

    SolicitudOperacion solicitud = buscarPorId(id);

    if (!"PENDIENTE".equals(solicitud.getEstado())) {
      throw new IllegalArgumentException(
          "La solicitud ya fue resuelta.");
    }

    Administrador administrador = administradorDAO.buscarPorId(idAdministrador)
        .orElseThrow(() -> new IllegalArgumentException(
            "Administrador no encontrado."));

    solicitud.setAdministrador(administrador);
    solicitud.setEstado(estado);
    solicitud.setObservaciones(observaciones);
    solicitud.setResueltaEn(LocalDateTime.now());

    return solicitudDAO.guardar(solicitud);
  }

  // LISTAR POR DESTINO
  @Transactional(readOnly = true)
  public List<SolicitudOperacion> listarPorDestino(Long idDestino) {
    return solicitudDAO.listarPorDestino(idDestino);
  }

  // LISTAR POR ESTADO
  @Transactional(readOnly = true)
  public List<SolicitudOperacion> listarPorEstado(String estado) {
    if (estado == null || !ESTADOS_VALIDOS.contains(estado)) {
      throw new IllegalArgumentException("Estado inválido.");
    }

    return solicitudDAO.listarPorEstado(estado);
  }

  // LISTAR POR DESTINO Y ESTADO
  @Transactional(readOnly = true)
  public List<SolicitudOperacion> listarPorDestinoYEstado(
      Long idDestino,
      String estado) {
    if (estado == null || !ESTADOS_VALIDOS.contains(estado)) {
      throw new IllegalArgumentException("Estado inválido.");
    }

    return solicitudDAO.listarPorDestinoYEstado(idDestino, estado);
  }

  // ELIMINAR
  @Transactional
  public void eliminar(Long id) {
    if (!solicitudDAO.existePorId(id)) {
      throw new IllegalArgumentException(
          "Solicitud no encontrada.");
    }

    solicitudDAO.eliminarPorId(id);
  }
}
