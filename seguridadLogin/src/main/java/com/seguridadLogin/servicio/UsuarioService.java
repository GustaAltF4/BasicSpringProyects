package com.seguridadLogin.servicio;

import com.seguridadLogin.entidades.Rol;
import com.seguridadLogin.entidades.Usuario;
import com.seguridadLogin.repositorio.RolRepo;
import com.seguridadLogin.repositorio.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepo repository;

    @Autowired
    private RolRepo rolRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario buscarPorNombre(String username) {
        return repository.findByUsername(username);
    }


    public void guardar(Usuario user){

        for (Rol rol: user.getRoles()){
            if (rol.getId() == null){
                rolRepository.save(rol);
            }
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        //rol por defecto
        Set<Rol> roles = new HashSet<>();
        roles.add(new Rol("ROLE_USER"));
        //asignar roles de administrador
        if (user.getUsername().equals("Gustavo")){
            roles.add(new Rol("ROLE_ADMIN"));
        }

        user.setRoles(roles);
        repository.save(user);

    }



}
