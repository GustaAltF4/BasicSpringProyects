package com.example.Tareas.controller;

import com.example.Tareas.model.Tarea;
import com.example.Tareas.model.Usuario;
import com.example.Tareas.repositorio.UsuarioRepository;
import com.example.Tareas.servicios.TareaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaServicio tareaServicio;

    @Autowired
    private UsuarioRepository usuarioRepository;


    private Usuario getUsuarioAutenticado() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        return usuarioRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
    }

    @GetMapping
    public String mostrarTareas(Model model){
        Usuario usuario= getUsuarioAutenticado();
        model.addAttribute("tareas", tareaServicio.listarPorPropietario(usuario));
        return "tareas";

    }



    @GetMapping("/new")
    public String MostrarFormularioAgregarTarea(Model model){
        model.addAttribute("tarea",new Tarea());
        return "agregarTarea";
    }

    @PostMapping
    public String agregarTarea(Tarea tareaNueva){
        Usuario usuario= getUsuarioAutenticado();
        tareaNueva.setPropietario(usuario);
        tareaServicio.guardarTarea(tareaNueva);
        return "redirect:/tareas";
    }

    @GetMapping("/edit/{id}")
    public String mostrarFormularioEditarTarea(@PathVariable("id") Integer id, Model model){
        Tarea tarea = tareaServicio.obtenerTareaPorId(id);

        if (tarea != null){
            model.addAttribute("tarea",tarea);
            return "editarTarea";

        }
        return "redirect:/tareas";

    }

    @PostMapping("/actualizar/{id}")
    public String actualizarTarea(@PathVariable("id") Integer id,@ModelAttribute Tarea tarea){
        Usuario usuario= getUsuarioAutenticado();
        tarea.setId(id);
        tarea.setPropietario(usuario);
        tareaServicio.guardarTarea(tarea);
        return "redirect:/tareas";
    }

    @GetMapping ("/borrar/{id}")
    public String borrarTarea(@PathVariable("id") Integer id){
        tareaServicio.borrarTarea(id);
        return "redirect:/tareas";
    }





}
