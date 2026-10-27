
package com.example.Proyecto_Viajo.repository;

import com.example.Proyecto_Viajo.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository
    extends JpaRepository<Publicacion, Long> {
}
