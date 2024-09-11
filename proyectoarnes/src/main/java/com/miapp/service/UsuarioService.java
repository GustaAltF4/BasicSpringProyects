package com.miapp.service;

import com.miapp.model.Usuarios;
import com.miapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public  Usuarios registrarUsuario(Usuarios usuario) {
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        return usuarioRepository.save(usuario);
    }

    public Usuarios obtenerPorNombre(String nombre) {
        return usuarioRepository.findByUsername(nombre).orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + nombre));

    }


}
