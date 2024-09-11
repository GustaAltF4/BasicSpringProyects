package com.notas.repository;

import com.notas.entidades.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Integer> {

    /*¿Qué Hace JpaRepository?
    JpaRepository proporciona una serie de métodos predefinidos para realizar operaciones básicas con la base de datos, como:

    findAll(): Obtiene todas las instancias de Nota.
    findById(Integer id): Busca una instancia de Nota por su identificador.
    save(Nota nota): Guarda o actualiza una instancia de Nota.
            deleteById(Integer id): Elimina una instancia de Nota por su identificador.
    No es necesario implementar estos métodos; JpaRepository se encarga de proporcionar la implementación automáticamente. Puedes agregar métodos adicionales en esta interfaz si necesitas consultas más específicas o personalizadas.

    Por ejemplo, si quisieras agregar un método para encontrar notas por título, podrías hacerlo así:

    Java

    public interface NotaRepository extends JpaRepository<Nota, Integer> {
        List<Nota> findByTitulo(String titulo);
    }
    Este método adicional permite encontrar todas las notas que tengan un título específico.*/
}
