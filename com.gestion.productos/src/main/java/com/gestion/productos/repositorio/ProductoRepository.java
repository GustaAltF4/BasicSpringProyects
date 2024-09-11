package com.gestion.productos.repositorio;

import com.gestion.productos.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {


}
