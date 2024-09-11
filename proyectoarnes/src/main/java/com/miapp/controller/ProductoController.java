package com.miapp.controller;

import com.miapp.model.Productos;
import com.miapp.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

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

    //crear un producto
    @PostMapping
    public ResponseEntity<Productos> crearProducto(@RequestBody Productos producto) {
        Productos prodnuevo= productoService.saveProducto(producto);
        return ResponseEntity.ok(prodnuevo);
    }

    //actualizar producto
    @PutMapping("/{id}")
    public  ResponseEntity<Productos> actualizarProducto(@PathVariable Integer id,@RequestBody Productos prodActualizado){
        Productos prodBorrar= productoService.getProductoById(id);
        if(prodBorrar!=null){
            prodActualizado.setId(id);
            Productos prodActulizadoGuardado= productoService.saveProducto(prodActualizado);
            return ResponseEntity.ok(prodActulizadoGuardado);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //eliminar un producto

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id){
        Productos prodBorrar= productoService.getProductoById(id);
        if(prodBorrar!=null){
            productoService.deleteProducto(id);
            return ResponseEntity.noContent().build();

        }else{
            return ResponseEntity.notFound().build();
        }


    }




}
