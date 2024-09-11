package com.blog.demo.controlador;

import com.blog.demo.dto.ComentarioDto;
import com.blog.demo.servicio.ComentarioServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ComentarioControlador {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDto> listarComentariosPorPublicacion(@PathVariable(value = "publicacionId")long publicacionId){
        return comentarioServicio.obtenerComentariosPorPublicacionId(publicacionId);
    }

    @GetMapping("/publicaciones/{publicacionId}/comentarios/{Id}")
    public ResponseEntity<ComentarioDto> obtenerComentarioPorId(
            @PathVariable(value = "publicacionId") long publicacionId,
            @PathVariable(value = "Id") long comentarioId){

        ComentarioDto comentarioDto= comentarioServicio.obtenerComentarioPorId(publicacionId,comentarioId);

        return new ResponseEntity<>(comentarioDto,HttpStatus.OK);
    }

    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDto> guardarComentario( @PathVariable(value = "publicacionId") Long publicacionId, @Valid @RequestBody ComentarioDto comentarioDto){

        return new ResponseEntity<>(comentarioServicio.crearComentario(publicacionId,comentarioDto), HttpStatus.CREATED);
    }

    @PutMapping("/publicaciones/{publicacionId}/comentarios/{Id}")
    public ResponseEntity<ComentarioDto> actualizarComentario(
            @PathVariable(value = "publicacionId") long publicacionId,
            @PathVariable(value = "Id") long comentarioId,
            @Valid @RequestBody ComentarioDto comentarioDto){

        ComentarioDto comentarioActualizado= comentarioServicio.actualizarComentario(publicacionId,comentarioId,comentarioDto);
        return new ResponseEntity<>(comentarioActualizado,HttpStatus.OK);

    }

    @DeleteMapping("/publicaciones/{publicacionId}/comentarios/{Id}")
    public ResponseEntity<String> eliminarComentario(@PathVariable(value = "publicacionId") long publicacionId, @PathVariable(value = "Id") long comentarioId){
        comentarioServicio.eliminarComentario(publicacionId,comentarioId);
        return new ResponseEntity<>("Comentario Eliminado Exitosamente",HttpStatus.OK);
    }

}
