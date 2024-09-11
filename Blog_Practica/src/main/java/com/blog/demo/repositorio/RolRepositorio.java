package com.blog.demo.repositorio;

import com.blog.demo.entidades.Rol;
import com.blog.demo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepositorio extends JpaRepository<Rol, Long> {

    public Optional<Rol> findByNombre(String nombre);
}
