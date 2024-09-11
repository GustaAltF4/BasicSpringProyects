package com.sistema.trailers.controladores;

import com.sistema.trailers.modelo.Genero;
import com.sistema.trailers.modelo.Pelicula;
import com.sistema.trailers.repositorio.GeneroRepositorio;
import com.sistema.trailers.repositorio.PeliculaRepositorio;
import com.sistema.trailers.servicio.AlmacenServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    @Autowired
    private GeneroRepositorio generoRepositorio;
    @Autowired
    private AlmacenServicioImpl servicio;



    @GetMapping("")
    public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "titulo", size = 5) Pageable pageable) {

        Page<Pelicula> peliculas = peliculaRepositorio.findAll(pageable);
        return new ModelAndView("admin/index").addObject("peliculas", peliculas);
    }

    @GetMapping("/peliculas/nuevo")
    public ModelAndView mostrarFormularioDeNuevaPelicula() {
        List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));

        return new ModelAndView("admin/nueva-pelicula")
                .addObject("pelicula", new Pelicula())
                .addObject("generos", generos);
    }

    @PostMapping("/peliculas/nuevo")
    public ModelAndView registrarPelicula(@ModelAttribute("pelicula") @Validated Pelicula pelicula, BindingResult result) {
        if(result.hasErrors() || pelicula.getPortada().isEmpty()){
            if(pelicula.getPortada() == null ||pelicula.getPortada().isEmpty()){
                result.rejectValue("portada", "MultipartNotEmpty");
            }
            List<Genero> generos= generoRepositorio.findAll(Sort.by("titulo"));
            System.out.println("Errors: " + result.getAllErrors());
            return new ModelAndView("admin/nueva-pelicula")
                    .addObject("pelicula", pelicula)
                    .addObject("generos", generos);
        }

        String rutaPortada = servicio.almacenarArchivo(pelicula.getPortada());
        pelicula.setRutaPortada(rutaPortada);

        peliculaRepositorio.save(pelicula);

        return new ModelAndView("redirect:/admin");

    }
    @GetMapping("/peliculas/{id}/editar")
    public ModelAndView mostrarFormularioDeEditarPelicula(@PathVariable Integer id){
        Pelicula pelicula= peliculaRepositorio.getOne(id);
        List<Genero> generos= generoRepositorio.findAll(Sort.by("titulo"));

        return new ModelAndView("admin/editar-pelicula")
                .addObject("pelicula", pelicula)
                .addObject("generos", generos);

    }

    @PostMapping("/peliculas/{id}/editar")
    public ModelAndView actualizarPelicula(@PathVariable Integer id, @Validated Pelicula pelicula, BindingResult result){

        if(result.hasErrors()){
            List<Genero> generos= generoRepositorio.findAll(Sort.by("titulo"));
            return new ModelAndView("admin/editar-pelicula")
                    .addObject("pelicula", pelicula)
                    .addObject("generos", generos);

        }
        Pelicula peliculaDB= peliculaRepositorio.getOne(id);

        peliculaDB.setTitulo(pelicula.getTitulo());
        peliculaDB.setSinopsis(pelicula.getSinopsis());
        peliculaDB.setFechaEstreno(pelicula.getFechaEstreno());
        peliculaDB.setYoutubeTrailerId(pelicula.getYoutubeTrailerId());
        peliculaDB.setGeneros(pelicula.getGeneros());

        if (!pelicula.getPortada().isEmpty()){
            servicio.eliminarArchivo(peliculaDB.getRutaPortada());
            String rutaPortada = servicio.almacenarArchivo(pelicula.getPortada());
            peliculaDB.setRutaPortada(rutaPortada);
        }

        peliculaRepositorio.save(peliculaDB);

        return new ModelAndView("redirect:/admin");


    }

    @PostMapping("/peliculas/{id}/eliminar")
    public String eliminarPelicula(@PathVariable Integer id){
        Pelicula pelicula= peliculaRepositorio.getOne(id);
        peliculaRepositorio.delete(pelicula);
        servicio.eliminarArchivo(pelicula.getRutaPortada());

        return "redirect:/admin";
    }


}
