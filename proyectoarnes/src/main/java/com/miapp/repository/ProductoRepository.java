package com.miapp.repository;

import com.miapp.model.Productos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductoRepository extends JpaRepository<Productos, Integer> {
    List<Productos> findByCategoriaArnesNombre(String nombre);
}
