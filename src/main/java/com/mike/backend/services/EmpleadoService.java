package com.mike.backend.services;

import com.mike.backend.dtos.EmpleadoDTO;
import com.mike.backend.entities.Empleado;

import org.springframework.data.domain.Page;


public interface EmpleadoService {
    
    // Aquí solo declaramos QUÉ vamos a hacer (nuestro menú de operaciones)
    EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO);

    // Le agregamos el parámetro "buscar" al final
    Page<Empleado> listarPaginados(int page, int size, String buscar);
    
    EmpleadoDTO obtenerEmpleadoPorId(Long id);
    
    EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO);
    
    void eliminarEmpleado(Long id);
}