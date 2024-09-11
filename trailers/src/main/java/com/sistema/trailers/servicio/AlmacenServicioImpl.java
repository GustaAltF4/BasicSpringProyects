package com.sistema.trailers.servicio;

import com.sistema.trailers.excepciones.AlmacenException;
import com.sistema.trailers.excepciones.FileNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class AlmacenServicioImpl implements AlmacenServicio{

    @Value("${storage.location}")
    private String storageLocation;

    @PostConstruct
    @Override
    public void iniciarAlmacenDeArchivos() {
        try{
            Files.createDirectories(Paths.get(storageLocation));
        }catch (IOException exception){
            throw new AlmacenException("Error al iniciar la ubicación en el almacén de Archivos");
        }

    }

    @Override
    public String almacenarArchivo(MultipartFile archivo) {
        String nombreArchivo = archivo.getOriginalFilename();
        if (archivo.isEmpty()){
            throw new AlmacenException("No se puede almacenar un archivo vacío");
        }
        try {
            InputStream inputStream = archivo.getInputStream();
            Files.copy(inputStream, Paths.get(storageLocation).resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException exception){
            throw new AlmacenException("Error al almacenar el archivo"+ nombreArchivo, exception);
        }
        return nombreArchivo;
    }

    @Override
    public Path cargarArchivo(String nombreDelArchivo) {

        return Paths.get(storageLocation).resolve(nombreDelArchivo);
    }

    @Override
    public Resource cargarComoRecurso(String nombreDelArchivo) {
        try {
            Path archivo= cargarArchivo(nombreDelArchivo);
            Resource recurso= new UrlResource(archivo.toUri());
            if (recurso.exists() || recurso.isReadable()){
                return recurso;
            }else {
                throw new FileNotFoundException("No se pudo encontrar el archivo "+ nombreDelArchivo);
            }
        }catch (MalformedURLException exception){
            throw new FileNotFoundException("No se pudo encontrar el archivo "+ nombreDelArchivo, exception);
        }
    }

    @Override
    public void eliminarArchivo(String nombreDelArchivo) {
        Path archivo = cargarArchivo(nombreDelArchivo);
        try {
            FileSystemUtils.deleteRecursively(archivo);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
