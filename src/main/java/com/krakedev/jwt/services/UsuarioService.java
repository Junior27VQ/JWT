package com.krakedev.jwt.services;

import org.springframework.stereotype.Service;
import com.krakedev.jwt.entidades.Usuario;
import com.krakedev.jwt.repositories.UsuarioRepository;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;

    // Inyección de dependencias por constructor
    public UsuarioService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    // Método de autenticación
    public Usuario autenticar(String username, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepo.findByUsername(username);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Validación básica de contraseña
            if (usuario.getPassword().equals(password)) {
                return usuario;
            }
        }
        return null; // O lanzar una excepción personalizada si lo prefieres
    }
}