package com.seguridadLogin.servicio;

import com.seguridadLogin.entidades.Usuario;
import com.seguridadLogin.repositorio.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailsServicePersonalizado implements UserDetailsService {

    @Autowired
    private UsuarioRepo repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario= repository.findByUsername(username);
        if (usuario==null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new User(username, usuario.getPassword(), usuario.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList()));
    }
}
