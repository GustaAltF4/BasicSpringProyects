package com.blog.demo.repositorio;

import com.blog.demo.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepositorio extends JpaRepository<Comentario, Long> {
    public List<Comentario> findByPublicacionId(Long publicacionId);
}
