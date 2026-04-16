package com.mike.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empleados")
@Getter // Lombok crea los getters en automático
@Setter // Lombok crea los setters en automático
@NoArgsConstructor // Lombok crea el constructor vacío
@AllArgsConstructor // Lombok crea el constructor con todos los campos
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(nullable = false, length = 100)
    private String departamento;
}