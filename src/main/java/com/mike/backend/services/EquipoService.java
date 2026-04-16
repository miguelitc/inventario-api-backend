package com.mike.backend.services;

import com.mike.backend.dtos.EquipoDTO;
import java.util.List;

public interface EquipoService {
    
    EquipoDTO crearEquipo(EquipoDTO equipoDTO);
    
    // Para los equipos usaremos una lista normal, ya que las instrucciones
    // no pidieron paginación específica para esta tabla
    List<EquipoDTO> listarEquipos();
    
    EquipoDTO obtenerEquipoPorId(Long id);
    
    EquipoDTO actualizarEquipo(Long id, EquipoDTO equipoDTO);
    
    void eliminarEquipo(Long id);
}