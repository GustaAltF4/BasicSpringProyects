package com.miapp.service;

import com.miapp.model.Productos;
import com.miapp.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Productos> getAllProductos(){
        return productoRepository.findAll();
    }

    public Productos getProductoById(Integer id){

        return productoRepository.findById(id).orElse(null);
    }

    public Productos saveProducto(Productos producto){

        return productoRepository.save(producto);

    }

    public void deleteProducto(Integer id){
        if(productoRepository.existsById(id)){
            productoRepository.deleteById(id);
            System.out.println("Producto eliminado");
        }else {
            System.out.println("Producto no encontrado");
        }

    }

    public List<Productos> getProductosByCategoria(String categoria){
        return productoRepository.findByCategoriaArnesNombre(categoria);
    }
}
