
package com.example.Proyecto_Viajo.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

  private final Cloudinary cloudinary;

  public CloudinaryService(Cloudinary cloudinary) {
    this.cloudinary = cloudinary;
  }

  public String subirImagen(MultipartFile archivo) {

    try {

      Map resultado = cloudinary.uploader().upload(
          archivo.getBytes(),
          ObjectUtils.asMap(
              "folder", "viajo/destinos",
              "resource_type", "image"));

      return resultado.get("secure_url").toString();

    } catch (IOException e) {

      throw new RuntimeException(
          "Error al subir la imagen a Cloudinary: " + e.getMessage());
    }
  }
}
