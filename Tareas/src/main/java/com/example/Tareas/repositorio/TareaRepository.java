package com.example.Tareas.repositorio;

import com.example.Tareas.model.Tarea;
import com.example.Tareas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    List<Tarea> findByPropietario(Usuario propietario);
    Optional<Tarea> findByIdAndPropietario(Integer id, Usuario propietario);

}
