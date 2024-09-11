package com.pruebasUnitariasApiR.repository;

import com.pruebasUnitariasApiR.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepo extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByEmail(String email);
}
