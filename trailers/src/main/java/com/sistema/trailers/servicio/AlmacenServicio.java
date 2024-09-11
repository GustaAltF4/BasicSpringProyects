package com.sistema.trailers.servicio;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface AlmacenServicio {

    public void iniciarAlmacenDeArchivos();

    public String almacenarArchivo(MultipartFile archivo);

    public Path cargarArchivo(String nombreDelArchivo);

    public Resource cargarComoRecurso(String nombreDelArchivo);

    public void eliminarArchivo(String nombreDelArchivo);

}
