package com.mike.backend.services.impl;

import com.mike.backend.dtos.EquipoDTO;
import com.mike.backend.entities.Empleado;
import com.mike.backend.entities.Equipo;
import com.mike.backend.mappers.EquipoMapper;
import com.mike.backend.repositories.EmpleadoRepository;
import com.mike.backend.repositories.EquipoRepository;
import com.mike.backend.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    // ¡Inyectamos el repositorio de Empleado para poder buscar al dueño!
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public EquipoDTO crearEquipo(EquipoDTO equipoDTO) {
        Equipo equipo = EquipoMapper.toEntity(equipoDTO);

        // Lógica clave: Si React nos mandó un empleado con ID válido, lo buscamos
        if (equipoDTO.getEmpleado() != null && equipoDTO.getEmpleado().getId() != null) {
            
            Long idEmpleado = equipoDTO.getEmpleado().getId();
            
            Empleado empleadoDb = empleadoRepository.findById(idEmpleado)
                    .orElseThrow(() -> new RuntimeException("Error: Empleado no encontrado con ID " + idEmpleado));
            
            // Le asignamos el empleado verificado por la BD al equipo
            equipo.setEmpleado(empleadoDb);
        } else {
            // Si nos mandan null, aseguramos que nazca "Sin asignar"
            equipo.setEmpleado(null);
        }

        Equipo equipoGuardado = equipoRepository.save(equipo);
        return EquipoMapper.toDTO(equipoGuardado);
    }

    @Override
    public List<EquipoDTO> listarEquipos() {
        List<Equipo> equipos = equipoRepository.findAll();
        // Convertimos la lista de Entidades a una lista de DTOs usando streams
        return equipos.stream()
                .map(EquipoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EquipoDTO obtenerEquipoPorId(Long id) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Equipo no encontrado con ID " + id));
        return EquipoMapper.toDTO(equipo);
    }

    @Override
    public EquipoDTO actualizarEquipo(Long id, EquipoDTO equipoDTO) {
        // 1. Buscamos el equipo en la BD
        Equipo equipoExistente = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Equipo no encontrado con ID " + id));

        // 2. Actualizamos los datos básicos
        equipoExistente.setNombreEquipo(equipoDTO.getNombreEquipo());
        equipoExistente.setTipo(equipoDTO.getTipo());
        equipoExistente.setNumeroSerie(equipoDTO.getNumeroSerie());

        // 3. Actualizamos al empleado (¡AQUÍ ESTÁ EL CAMBIO CLAVE!)
        if (equipoDTO.getEmpleado() != null && equipoDTO.getEmpleado().getId() != null) {
            
            // Extraemos el ID del objeto empleado que viene en el DTO
            Long idEmpleado = equipoDTO.getEmpleado().getId(); 
            
            Empleado empleadoDb = empleadoRepository.findById(idEmpleado)
                    .orElseThrow(() -> new RuntimeException("Error: Empleado no encontrado con ID " + idEmpleado));
            
            equipoExistente.setEmpleado(empleadoDb);
            
        } else {
            // Si mandan null, significa que le quitaron el equipo al empleado (vuelve a Stock)
            equipoExistente.setEmpleado(null);
        }

        // 4. Guardamos y convertimos de regreso a DTO para enviarlo a React
        Equipo equipoActualizado = equipoRepository.save(equipoExistente);
        return EquipoMapper.toDTO(equipoActualizado);
    }

    @Override
    public void eliminarEquipo(Long id) {
        equipoRepository.deleteById(id);
    }
}