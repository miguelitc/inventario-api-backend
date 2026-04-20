package com.mike.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Este será el usuario para loguearse (ej. "admin")
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    // Aquí guardaremos la contraseña ENCRIPTADA (nunca en texto plano)
    @Column(nullable = false)
    private String password;

    // Por si después quieres tener cajeros y administradores
    @Column(nullable = false)
    private String rol; 
}