package com.example.Proyecto_Viajo.repository;

import com.example.Proyecto_Viajo.model.RolProceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolProcesoRepository
    extends JpaRepository<RolProceso, String> {
}
