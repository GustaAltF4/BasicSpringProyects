package com.miapp.repository;

import com.miapp.model.PedidoProducto;
import com.miapp.model.PedidoProductoKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, PedidoProductoKey> {
}
