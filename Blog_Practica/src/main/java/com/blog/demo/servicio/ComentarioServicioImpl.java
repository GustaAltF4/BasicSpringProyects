package com.blog.demo.servicio;

import com.blog.demo.dto.ComentarioDto;
import com.blog.demo.entidades.Comentario;
import com.blog.demo.entidades.Publicacion;
import com.blog.demo.excepciones.BlogAppException;
import com.blog.demo.excepciones.ResourceNotFoundException;
import com.blog.demo.repositorio.ComentarioRepositorio;
import com.blog.demo.repositorio.PublicacionRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{
    @Autowired
    private ComentarioRepositorio comentarioRepositorio;
    @Autowired
    private PublicacionRepositorio publicacionRepositorio;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ComentarioDto crearComentario(long publicacionID, ComentarioDto comentarioDto) {
        Comentario comentario= mapearEntidad(comentarioDto);

        Publicacion publicacion= publicacionRepositorio.findById(publicacionID).orElseThrow(()-> new ResourceNotFoundException("Publicacion","id",publicacionID));

        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario= comentarioRepositorio.save(comentario);
        return mapearDto(nuevoComentario);
    }

    @Override
    public List<ComentarioDto> obtenerComentariosPorPublicacionId(long publicacionId) {
        List<Comentario> comentarios= comentarioRepositorio.findByPublicacionId(publicacionId);
        return comentarios.stream().map(comentario -> mapearDto(comentario)).collect(Collectors.toList());
    }

    @Override
    public ComentarioDto obtenerComentarioPorId(long publicacionId, long comentarioId) {
        Publicacion publicacion= publicacionRepositorio.findById(publicacionId)
                .orElseThrow(()-> new ResourceNotFoundException("Publicacion","id",publicacionId));

        Comentario comentario= comentarioRepositorio.findById(comentarioId)
                .orElseThrow(()-> new ResourceNotFoundException("Comentario","id",comentarioId));

        if (!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogAppException("El comentario no pertenece a esta publicacion", HttpStatus.BAD_REQUEST);
        }
        return mapearDto(comentario);
    }

    @Override
    public ComentarioDto actualizarComentario(long publicacionId,long comentarioId, ComentarioDto solicitudDeComentario) {
        Publicacion publicacion= publicacionRepositorio.findById(publicacionId)
                .orElseThrow(()-> new ResourceNotFoundException("Publicacion","id",publicacionId));

        Comentario comentario= comentarioRepositorio.findById(comentarioId)
                .orElseThrow(()-> new ResourceNotFoundException("Comentario","id",comentarioId));

        if (!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogAppException("El comentario no pertenece a esta publicacion", HttpStatus.BAD_REQUEST);
        }

        comentario.setNombre(solicitudDeComentario.getNombre());
        comentario.setEmail(solicitudDeComentario.getEmail());
        comentario.setCuerpo(solicitudDeComentario.getCuerpo());

        Comentario comentarioActualizado= comentarioRepositorio.save(comentario);
        return mapearDto(comentarioActualizado);
    }

    @Override
    public void eliminarComentario(long publicacionId, long comentarioId) {
        Publicacion publicacion= publicacionRepositorio.findById(publicacionId)
                .orElseThrow(()-> new ResourceNotFoundException("Publicacion","id",publicacionId));

        Comentario comentario= comentarioRepositorio.findById(comentarioId)
                .orElseThrow(()-> new ResourceNotFoundException("Comentario","id",comentarioId));

        if (!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogAppException("El comentario no pertenece a esta publicacion", HttpStatus.BAD_REQUEST);
        }
        comentarioRepositorio.delete(comentario);

    }

    private ComentarioDto mapearDto(Comentario comentario){
        ComentarioDto comentarioDto= modelMapper.map(comentario,ComentarioDto.class);

        return comentarioDto;

    }
    private Comentario mapearEntidad(ComentarioDto comentarioDto){
        Comentario comentario= modelMapper.map(comentarioDto,Comentario.class);

        return comentario;

    }
}
