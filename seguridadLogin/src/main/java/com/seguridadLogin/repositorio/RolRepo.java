package com.seguridadLogin.repositorio;

import com.seguridadLogin.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepo extends JpaRepository<Rol, Integer> {
}
