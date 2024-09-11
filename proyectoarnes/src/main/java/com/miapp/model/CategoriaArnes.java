package com.miapp.model;

import jakarta.persistence.*;

@Entity
@Table(name="categoria_arnes")
public class CategoriaArnes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    //@Column(name="nombre")
    @Column(nullable = false,unique = true)
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
