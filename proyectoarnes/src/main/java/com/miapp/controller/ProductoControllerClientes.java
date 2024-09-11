package com.miapp.controller;

import com.miapp.model.Productos;
import com.miapp.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productosver")
public class ProductoControllerClientes {

    @Autowired
    private ProductoService productoService;

    //prueba leer producto
    @GetMapping("/{id}")
    public ResponseEntity<Productos> getProductosPorID(@PathVariable Integer id) {
        Productos prod1= productoService.getProductoById(id);
        if (prod1 != null) {
            return ResponseEntity.ok(prod1);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //Leer todos los productos
    @GetMapping
    public ResponseEntity<List<Productos>> obternerTodosLosProductos() {
        List<Productos> ListaProdCompleta= productoService.getAllProductos();
        return ResponseEntity.ok(ListaProdCompleta);
    }





}
