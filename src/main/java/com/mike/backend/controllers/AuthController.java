package com.mike.backend.controllers;

import com.mike.backend.entities.Usuario;
import com.mike.backend.repositories.UsuarioRepository;
import com.mike.backend.security.AuthRequest;
import com.mike.backend.security.AuthResponse;
import com.mike.backend.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = {"http://localhost:5173", "https://inventario-react-frontend.vercel.app"})
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        // 1. Verificamos que el usuario y contraseña sean correctos
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // 2. Si pasó, buscamos al usuario en la BD
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        
        // 3. Convertimos a UserDetails (el formato que entiende Spring)
        UserDetails userDetails = User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRol())
                .build();

        // 4. Generamos el gafete (Token) y se lo mandamos a React
        String token = jwtService.generarToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // 🎁 REGALO DE SENIOR: Un endpoint temporal para crear tu primer usuario administrador
    @PostMapping("/registrar-admin")
    public ResponseEntity<String> registrarAdmin(@RequestBody AuthRequest request) {
        Usuario nuevoAdmin = new Usuario();
        nuevoAdmin.setUsername(request.getUsername());
        // ENCRIPTAMOS la contraseña antes de guardarla en Neon
        nuevoAdmin.setPassword(passwordEncoder.encode(request.getPassword())); 
        nuevoAdmin.setRol("ADMIN");
        
        usuarioRepository.save(nuevoAdmin);
        return ResponseEntity.ok("Administrador creado exitosamente");
    }
}