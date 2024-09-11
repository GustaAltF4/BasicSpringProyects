package com.gestion.productos.servicio;

import com.gestion.productos.modelo.Producto;
import com.gestion.productos.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepository productoRepositorio;

    public List<Producto> listarProductos(){
        return productoRepositorio.findAll();
    }

    public void guardarProducto(Producto producto){
        productoRepositorio.save(producto);
    }

    public Producto obtenerProducto(Integer id){
       return productoRepositorio.findById(id).get();
    }

    public void eliminarProducto(Integer id){
        productoRepositorio.deleteById(id);
    }

}
