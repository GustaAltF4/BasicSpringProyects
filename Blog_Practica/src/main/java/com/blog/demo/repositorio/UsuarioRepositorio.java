package com.blog.demo.repositorio;

import com.blog.demo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

    public Optional<Usuario> findByUsernameOrEmail(String username,String email);

    public Optional<Usuario> findByUsername(String username);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);
}
