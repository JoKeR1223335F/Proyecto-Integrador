package com.example.Proyecto_Viajo.repository;

import com.example.Proyecto_Viajo.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository
    extends JpaRepository<Administrador, Long> {
}
