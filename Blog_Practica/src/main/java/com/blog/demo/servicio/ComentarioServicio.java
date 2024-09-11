package com.blog.demo.servicio;

import com.blog.demo.dto.ComentarioDto;
import com.blog.demo.entidades.Comentario;

import java.util.List;

public interface ComentarioServicio {

    public ComentarioDto crearComentario(long publicacionID, ComentarioDto comentarioDto);

    public List<ComentarioDto> obtenerComentariosPorPublicacionId(long publicacionId);

    public ComentarioDto obtenerComentarioPorId(long publicacionId, long comentarioId);

    public ComentarioDto actualizarComentario(long publicacionId ,long comentarioId,ComentarioDto solicitudDeComentario);

    public void eliminarComentario(long publicacionId, long comentarioId);
}
