package com.mike.backend.repositories;

import com.mike.backend.entities.Empleado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Le dice a Spring que este es nuestro conector de base de datos
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    
    // Busca por nombre, ignorando mayúsculas y minúsculas, y lo devuelve paginado
    Page<Empleado> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}