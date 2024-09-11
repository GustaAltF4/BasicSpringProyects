package com.seguridadLogin.repositorio;

import com.seguridadLogin.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
}
