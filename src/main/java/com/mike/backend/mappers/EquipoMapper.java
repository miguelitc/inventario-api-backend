package com.mike.backend.mappers;

import com.mike.backend.dtos.EquipoDTO;
import com.mike.backend.entities.Equipo;

public class EquipoMapper {

    // Convierte de Entidad a DTO
    public static EquipoDTO toDTO(Equipo equipo) {
        if (equipo == null) {
            return null;
        }
        
        Long empleadoId = null;
        // Revisamos si el equipo tiene un empleado asignado para sacar su ID
        if (equipo.getEmpleado() != null) {
            empleadoId = equipo.getEmpleado().getId();
        }

        return new EquipoDTO(
            equipo.getId(),
            equipo.getNombreEquipo(),
            equipo.getTipo(),
            equipo.getNumeroSerie(),
            equipo.getEmpleado() // ¡ESTA ES LA MAGIA!
        );
    }

    // Convierte de DTO a Entidad
    public static Equipo toEntity(EquipoDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Equipo equipo = new Equipo();
        equipo.setId(dto.getId());
        equipo.setNombreEquipo(dto.getNombreEquipo());
        equipo.setTipo(dto.getTipo());
        equipo.setNumeroSerie(dto.getNumeroSerie());
        
        // Nota: El empleado lo asignaremos en la capa Service buscando el ID en la BD
        return equipo;
    }
}