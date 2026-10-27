
package com.example.Proyecto_Viajo.repository;

import com.example.Proyecto_Viajo.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository
    extends JpaRepository<Notificacion, Long> {
}
