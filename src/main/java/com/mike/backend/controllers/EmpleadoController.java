package com.mike.backend.controllers;

import com.mike.backend.dtos.EmpleadoDTO;
import com.mike.backend.entities.Empleado;
import com.mike.backend.services.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Le dice a Spring que este es un mesero que sirve datos (JSON)
@RequestMapping("/api/v1/empleados") // La ruta principal para este mesero
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://localhost:5173") // Permite que React se conecte
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // POST: Para crear un empleado nuevo
    @PostMapping
    public ResponseEntity<EmpleadoDTO> crear(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
        return new ResponseEntity<>(empleadoService.crearEmpleado(empleadoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Empleado>> listarPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String buscar) {
        
        Page<Empleado> respuesta = empleadoService.listarPaginados(page, size, buscar);
        return ResponseEntity.ok(respuesta);
    }

    // GET por ID: Para buscar a uno solo (útil cuando Angular quiera editarlo)
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empleadoService.obtenerEmpleadoPorId(id));
    }

    // PUT: Para guardar los cambios cuando edites a un empleado
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EmpleadoDTO empleadoDTO) {
        return ResponseEntity.ok(empleadoService.actualizarEmpleado(id, empleadoDTO));
    }

    // DELETE: Para borrar a un empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}