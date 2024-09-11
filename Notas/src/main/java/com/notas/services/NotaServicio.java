package com.notas.services;

import com.notas.entidades.Nota;
import com.notas.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service: Indica que esta clase es un servicio en el contexto de Spring. Spring manejará la instancia de esta clase y la gestionará como un componente de servicio.
@Service
public class NotaServicio {

    @Autowired
    public NotaRepository notaRepository;

    public Nota guardarNota(Nota nota){
        return notaRepository.save(nota);
    }

    public void borrarNota(Integer id){
        notaRepository.deleteById(id);
    }

    public Nota buscarNota(Integer id){
        return notaRepository.findById(id).orElse(null);
    }
    public List<Nota> listarNotas(){
        return notaRepository.findAll();
    }
    /*
        La clase NotaServicio actúa como una capa de servicio entre el controlador y el repositorio.
        Proporciona métodos para guardar, eliminar, buscar y listar notas, delegando la interacción
        con la base de datos al NotaRepository.
        Esto permite mantener la lógica de negocio separada de la lógica de acceso a datos.
     */
}
