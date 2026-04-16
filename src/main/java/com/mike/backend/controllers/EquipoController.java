package com.mike.backend.controllers;

import com.mike.backend.dtos.EquipoDTO;
import com.mike.backend.services.EquipoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// ... tus otros imports
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/equipos")
//para dejar pasar a Angular
//@CrossOrigin(origins = "http://localhost:4200")s
//para dejar pasar a React
@CrossOrigin(origins = "http://localhost:5173")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @PostMapping
    public ResponseEntity<EquipoDTO> crear(@Valid @RequestBody EquipoDTO equipoDTO) {
       return new ResponseEntity<>(equipoService.crearEquipo(equipoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EquipoDTO>> listar() {
        return ResponseEntity.ok(equipoService.listarEquipos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(equipoService.obtenerEquipoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EquipoDTO equipoDTO) {
        return ResponseEntity.ok(equipoService.actualizarEquipo(id, equipoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        equipoService.eliminarEquipo(id);
        return ResponseEntity.noContent().build();
    }
}