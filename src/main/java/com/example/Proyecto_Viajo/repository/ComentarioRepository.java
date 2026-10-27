
package com.example.Proyecto_Viajo.repository;

import com.example.Proyecto_Viajo.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository
    extends JpaRepository<Comentario, Long> {
}
