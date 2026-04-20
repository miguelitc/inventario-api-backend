package com.mike.backend.repositories;

import com.mike.backend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Este método es oro molido. Spring lo usará para buscar al usuario por su nombre.
    Optional<Usuario> findByUsername(String username);
}