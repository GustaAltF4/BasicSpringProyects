package com.miapp.service;

import com.miapp.model.Usuarios;
import com.miapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPermitidosService implements UserDetailsService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuarios> user= usuarioRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("No se encontró el usuario: " + username);
        }

        Usuarios usuario= user.get();
        return User.withUsername(usuario.getNombre())
                .password(usuario.getContraseña())
                .roles(usuario.getRol())
                .build();
    }
}
