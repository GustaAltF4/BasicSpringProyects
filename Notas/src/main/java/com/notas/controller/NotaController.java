package com.notas.controller;

import com.notas.entidades.Nota;
import com.notas.services.NotaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
/*
    @Controller: Marca esta clase como un controlador de Spring, lo que significa que maneja las solicitudes HTTP.

    @RequestMapping("/notas"): Define la ruta base para todas las solicitudes manejadas por este controlador.
    Todas las rutas en los métodos del controlador serán relativas a /notas.

 */

@Controller
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    public NotaServicio notaServicio;

    /*
    El objeto Model es una interfaz que permite agregar atributos que se pasan desde el controlador a la vista.
    El Model actúa como un contenedor de datos que la vista puede utilizar para presentar información al usuario.
    Dentro de un método del controlador, puedes agregar atributos al Model utilizando el método addAttribute()

    model.addAttribute("nombre", "Valor");

    En este ejemplo, "nombre" es el nombre del atributo que se usará en la vista, y "Valor" es el valor asociado a este atributo.
    En la vista (por ejemplo, una plantilla Thymeleaf o JSP), puedes acceder a estos atributos utilizando
    el nombre con el que fueron agregados entre "${}".
     */

    @GetMapping
    public String listarNotas(Model model) {
        model.addAttribute("notas", notaServicio.listarNotas());
        return "notas/lista";
    }

    @GetMapping("/crear")
    public String formularioParaCrearNota(Nota nota, Model model) {
        model.addAttribute("nota", new Nota());
        return "notas/formulario";
    }
    /*
    Cuando se envía un formulario, los datos del formulario se pueden vincular automáticamente a un
    objeto de modelo en el controlador mediante @ModelAttribute.
    Esto se hace en el método del controlador que maneja la solicitud POST del formulario.

    @ModelAttribute Nota nota: Spring automáticamente crea una instancia de Nota y vincula los datos del formulario a sus campos.
    Por ejemplo, el valor del campo título en el formulario se asignará a la propiedad título del objeto Nota
     */

    @PostMapping("/guardar")
    public String guardarNota(@ModelAttribute Nota nota) {
        nota.setFechaCreacion(LocalDate.now());
        notaServicio.guardarNota(nota);
        return "redirect:/notas";
    }

    @GetMapping("/editar/{id}")
    public String FormularioParaEditarNota(@PathVariable Integer id, Model model) {
        Nota nota= notaServicio.buscarNota(id);
        model.addAttribute("nota", nota);
        return "notas/formulario";
    }

    @GetMapping("/borrar/{id}")
    public String borrarNota(@PathVariable Integer id){
        notaServicio.borrarNota(id);
        return "redirect:/notas";
    }

    /*
        El NotaController maneja las solicitudes HTTP para la entidad Nota.
        Proporciona métodos para listar, crear, guardar, editar y eliminar notas,
        delegando la lógica de negocio al NotaServicio.

        Los métodos del controlador preparan los datos necesarios y determinan qué vista se debe
        mostrar o redirigen a otras rutas según la operación solicitada.

     */


}
