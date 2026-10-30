package com.example.Proyecto_Viajo.dto;

import java.time.LocalDateTime;

public record DestinoImagenDTO(
    Long idDestinoImagen,
    Long idDestino,
    String urlImagen,
    String textoAlternativo,
    Boolean esPrincipal,
    Integer orden,
    LocalDateTime creadoEn) {
}
