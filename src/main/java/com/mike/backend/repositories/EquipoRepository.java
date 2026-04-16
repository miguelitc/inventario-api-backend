package com.mike.backend.repositories;

import com.mike.backend.entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    // Aquí también ya tenemos todo el CRUD listo para usar.
    
}