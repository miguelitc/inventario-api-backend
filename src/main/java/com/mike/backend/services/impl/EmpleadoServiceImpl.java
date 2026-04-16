package com.mike.backend.services.impl;

import com.mike.backend.dtos.EmpleadoDTO;
import com.mike.backend.entities.Empleado;
import com.mike.backend.mappers.EmpleadoMapper;
import com.mike.backend.repositories.EmpleadoRepository;
import com.mike.backend.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service // ¡Súper importante! Le dice a Spring que esta clase maneja la lógica de negocio
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO) {
        // 1. Convertimos el DTO que llega a una Entidad real
        Empleado empleado = EmpleadoMapper.toEntity(empleadoDTO);
        
        // 2. Guardamos en la base de datos usando el Repositorio
        Empleado empleadoGuardado = empleadoRepository.save(empleado);
        
        // 3. Devolvemos el resultado convertido de nuevo a DTO
        return EmpleadoMapper.toDTO(empleadoGuardado);
    }

   @Override
    public Page<Empleado> listarPaginados(int page, int size, String buscar) {
        Pageable pageable = PageRequest.of(page, size);

        // Si mandaron una palabra para buscar, usamos el nuevo filtro
        if (buscar != null && !buscar.trim().isEmpty()) {
            return empleadoRepository.findByNombreContainingIgnoreCase(buscar, pageable);
        } else {
            // Si está vacío, traemos todos como siempre
            return empleadoRepository.findAll(pageable);
        }
    }

    @Override
    public EmpleadoDTO obtenerEmpleadoPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Empleado no encontrado con ID " + id));
        return EmpleadoMapper.toDTO(empleado);
    }

    @Override
    public EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO) {
        // 1. Buscamos si el empleado existe
        Empleado empleadoExistente = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Empleado no encontrado con ID " + id));

        // 2. Actualizamos los datos
        empleadoExistente.setNombre(empleadoDTO.getNombre());
        empleadoExistente.setCorreo(empleadoDTO.getCorreo());
        empleadoExistente.setDepartamento(empleadoDTO.getDepartamento());

        // 3. Guardamos los cambios
        Empleado empleadoActualizado = empleadoRepository.save(empleadoExistente);
        return EmpleadoMapper.toDTO(empleadoActualizado);
    }

    @Override
    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
}