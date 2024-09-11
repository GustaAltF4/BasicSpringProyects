package com.example.Tareas.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String description;
    private LocalDate fechaCreacion;
    private boolean completada;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario propietario;

    public Tarea() {
    }

    public Tarea(Integer id, String titulo, String description, LocalDate fechaCreacion, boolean completada, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.description = description;
        this.fechaCreacion = fechaCreacion;
        this.completada = completada;
        this.propietario = usuario;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
