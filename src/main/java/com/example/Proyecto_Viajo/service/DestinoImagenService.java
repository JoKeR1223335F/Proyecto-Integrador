package com.example.Proyecto_Viajo.service;

import com.example.Proyecto_Viajo.dao.DestinoImagenDAO;
import com.example.Proyecto_Viajo.dao.DestinoDAO;

import com.example.Proyecto_Viajo.model.Destino;
import com.example.Proyecto_Viajo.model.DestinoImagen;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class DestinoImagenService {

  private final DestinoImagenDAO imagenDAO;
  private final DestinoDAO destinoDAO;
  private final CloudinaryService cloudinaryService;

  public DestinoImagenService(
      DestinoImagenDAO imagenDAO,
      DestinoDAO destinoDAO,
      CloudinaryService cloudinaryService) {
    this.imagenDAO = imagenDAO;
    this.destinoDAO = destinoDAO;
    this.cloudinaryService = cloudinaryService;
  }

  @Transactional
  public DestinoImagen subirImagen(
      MultipartFile archivo,
      Long idDestino,
      String textoAlternativo,
      Boolean esPrincipal,
      Integer orden) {

    if (archivo == null || archivo.isEmpty()) {
      throw new IllegalArgumentException(
          "Debe seleccionar una imagen.");
    }

    if (archivo.getContentType() == null ||
        !archivo.getContentType().startsWith("image/")) {
      throw new IllegalArgumentException(
          "El archivo debe ser una imagen.");
    }

    if (archivo.getSize() > 5 * 1024 * 1024) {
      throw new IllegalArgumentException(
          "La imagen no debe superar los 5 MB.");
    }

    Destino destino = destinoDAO.buscarPorId(idDestino)
        .orElseThrow(() -> new IllegalArgumentException("El destino no existe."));

    String url = cloudinaryService.subirImagen(archivo);

    DestinoImagen imagen = new DestinoImagen();

    imagen.setDestino(destino);
    imagen.setUrlImagen(url);
    imagen.setTextoAlternativo(textoAlternativo);
    imagen.setEsPrincipal(esPrincipal != null && esPrincipal);
    imagen.setOrden(orden != null ? orden : 0);

    return imagenDAO.guardar(imagen);
  }

  @Transactional(readOnly = true)
  public List<DestinoImagen> listarPorDestino(Long idDestino) {
    return imagenDAO.listarPorDestino(idDestino);
  }

  @Transactional(readOnly = true)
  public DestinoImagen buscarPorId(Long id) {
    return imagenDAO.buscarPorId(id)
        .orElseThrow(() -> new IllegalArgumentException("La imagen no existe."));
  }
}
