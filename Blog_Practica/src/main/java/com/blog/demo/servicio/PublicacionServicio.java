package com.blog.demo.servicio;

import com.blog.demo.dto.PublicacionDto;
import com.blog.demo.dto.PublicacionRespuesta;



public interface PublicacionServicio {

    public PublicacionDto crearPublicacion(PublicacionDto publicacionDto);

    public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

    public PublicacionDto obtenerPublicacionPorId(Long id);

    public PublicacionDto actualizarPublicacion(PublicacionDto publicacionDto, Long id);

    public void eliminarPublicacion(Long id);
}
