package com.example.Tareas.servicios;

import com.example.Tareas.model.Tarea;

import com.example.Tareas.model.Usuario;
import com.example.Tareas.repositorio.TareaRepository;
import com.example.Tareas.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaServicio {

    @Autowired
    private TareaRepository tareaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Tarea> ListarTareas(){
        List<Tarea> tareas= tareaRepository.findAll();
        return tareas;
    }

    public Tarea obtenerTareaPorId(Integer id){
        //Tarea tarea= tareaRepository.findById(id).orElse(null);
        return tareaRepository.findById(id).orElse(null);
    }

    public Tarea guardarTarea(Tarea tarea){
        return tareaRepository.save(tarea);
    }

    public String borrarTarea(Integer id){
        tareaRepository.deleteById(id);
        return "Tarea borrada";
    }

    public List<Tarea> listarPorPropietario(Usuario usuario){
        return tareaRepository.findByPropietario(usuario);
    }


}
