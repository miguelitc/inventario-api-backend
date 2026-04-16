package com.mike.backend.dtos;

import com.mike.backend.entities.Empleado;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class EquipoDTO {

    private Long id;

    private Empleado empleado;

    @NotBlank(message = "El nombre del equipo es obligatorio")
    private String nombreEquipo;

    @NotBlank(message = "El tipo de equipo es obligatorio")
    private String tipo;

    @NotBlank(message = "El número de serie es obligatorio")
    private String numeroSerie;

    // Solo pedimos el ID del empleado para hacer la asignación desde Angular
    public EquipoDTO() {}

    public EquipoDTO(Long id, String nombreEquipo, String tipo, String numeroSerie, Empleado empleado) {
        this.id = id;
        this.nombreEquipo = nombreEquipo;
        this.tipo = tipo;
        this.numeroSerie = numeroSerie;
        this.empleado = empleado;
    }
}