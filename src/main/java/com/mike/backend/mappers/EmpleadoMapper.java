package com.mike.backend.mappers;

import com.mike.backend.dtos.EmpleadoDTO;
import com.mike.backend.entities.Empleado;

public class EmpleadoMapper {

    // Convierte de Entidad (Base de Datos) a DTO (Para Angular)
    public static EmpleadoDTO toDTO(Empleado empleado) {
        if (empleado == null) {
            return null;
        }
        return new EmpleadoDTO(
            empleado.getId(),
            empleado.getNombre(),
            empleado.getCorreo(),
            empleado.getDepartamento()
        );
    }

    // Convierte de DTO (Desde Angular) a Entidad (Para Base de Datos)
    public static Empleado toEntity(EmpleadoDTO dto) {
        if (dto == null) {
            return null;
        }
        Empleado empleado = new Empleado();
        empleado.setId(dto.getId());
        empleado.setNombre(dto.getNombre());
        empleado.setCorreo(dto.getCorreo());
        empleado.setDepartamento(dto.getDepartamento());
        return empleado;
    }
}