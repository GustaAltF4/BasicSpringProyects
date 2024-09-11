package com.blog.demo.servicio;

import com.blog.demo.dto.PublicacionDto;
import com.blog.demo.dto.PublicacionRespuesta;
import com.blog.demo.entidades.Publicacion;
import com.blog.demo.excepciones.ResourceNotFoundException;
import com.blog.demo.repositorio.PublicacionRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServicioImpl implements PublicacionServicio {

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PublicacionDto crearPublicacion(PublicacionDto publicacionDto) {
        //convertir DTO a entidad
        Publicacion publicacion = mapearEntidad(publicacionDto);

        //guardar publicacion en la base de datos
        Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);

        //convertir entidad a DTO
        PublicacionDto publicacionRespuesta = mapearDTO(nuevaPublicacion);

        return publicacionRespuesta;

        // En resumen, el método convierte un DTO recibido en una entidad, guarda esa entidad en la base de datos,
        // luego convierte la entidad guardada de vuelta en un DTO y lo devuelve.
        // Esto permite que la lógica de negocio se mantenga en la capa de servicio y que los datos se manejen
        // de manera estructurada y coherente entre las capas de la aplicación.
    }

    @Override
    public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina,String ordenarPor,String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
        Pageable pageable= PageRequest.of(numeroDePagina,medidaDePagina, sort);
        Page<Publicacion> publicaciones= publicacionRepositorio.findAll(pageable);

        List<Publicacion> ListaDepublicaciones =publicaciones.getContent();

        List<PublicacionDto> contenido= ListaDepublicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());

        PublicacionRespuesta publicacionRespuesta= new PublicacionRespuesta();
        publicacionRespuesta.setContenido(contenido);
        publicacionRespuesta.setNumeroDePagina(publicaciones.getNumber());
        publicacionRespuesta.setMedidaDePagina(publicaciones.getSize());
        publicacionRespuesta.setTotalElementos(publicaciones.getTotalElements());
        publicacionRespuesta.setTotalPaginas(publicaciones.getTotalPages());
        publicacionRespuesta.setUltima(publicaciones.isLast());

        return publicacionRespuesta;
    }

    @Override
    public PublicacionDto obtenerPublicacionPorId(Long id) {
        Publicacion publicacion= publicacionRepositorio.findById(id).orElseThrow(()-> new ResourceNotFoundException("Publicacion","id",id));
        return mapearDTO(publicacion);
    }

    @Override
    public PublicacionDto actualizarPublicacion(PublicacionDto publicacionDto, Long id) {
        Publicacion publicacion= publicacionRepositorio.findById(id).orElseThrow(()-> new ResourceNotFoundException("Publicacion","id",id));

        publicacion.setTitulo(publicacionDto.getTitulo());
        publicacion.setContenido(publicacionDto.getContenido());
        publicacion.setDescripcion(publicacionDto.getDescripcion());

        Publicacion publicacionActualizada= publicacionRepositorio.save(publicacion);

        return mapearDTO(publicacionActualizada);
    }

    @Override
    public void eliminarPublicacion(Long id) {
        Publicacion publicacion= publicacionRepositorio.findById(id).orElseThrow(()-> new ResourceNotFoundException("Publicacion","id",id));
        publicacionRepositorio.delete(publicacion);

    }

    //convierte entidad a DTO
    private PublicacionDto mapearDTO(Publicacion publicacion){
//        PublicacionDto publicacionDto = new PublicacionDto();
//        publicacionDto.setId(publicacion.getId());
//        publicacionDto.setTitulo(publicacion.getTitulo());
//        publicacionDto.setContenido(publicacion.getContenido());
//        publicacionDto.setDescripcion(publicacion.getDescripcion());
        PublicacionDto publicacionDto= modelMapper.map(publicacion,PublicacionDto.class);

        return publicacionDto;
    }
    //convierte DTO a entidad
    private Publicacion mapearEntidad(PublicacionDto publicacionDto){
//        Publicacion publicacion = new Publicacion();
//
//        publicacion.setTitulo(publicacionDto.getTitulo());
//        publicacion.setContenido(publicacionDto.getContenido());
//        publicacion.setDescripcion(publicacionDto.getDescripcion());
        Publicacion publicacion= modelMapper.map(publicacionDto,Publicacion.class);

        return publicacion;
    }
}
