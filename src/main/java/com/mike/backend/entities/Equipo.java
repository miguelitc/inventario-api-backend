package com.mike.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "equipos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // En Java usamos camelCase, pero le decimos a Postgres cómo se llama la columna real
    @Column(name = "nombre_equipo", nullable = false, length = 100)
    private String nombreEquipo;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(name = "numero_serie", nullable = false, unique = true, length = 50)
    private String numeroSerie;

    // --- LA RELACIÓN (Muchos equipos pertenecen a Un empleado) ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id")
    @JsonIgnoreProperties({"equipos", "hibernateLazyInitializer", "handler"})
    private Empleado empleado;
}