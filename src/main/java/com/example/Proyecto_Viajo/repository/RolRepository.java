
package com.example.Proyecto_Viajo.repository;

import com.example.Proyecto_Viajo.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
