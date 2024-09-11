package com.pruebasUnitariasApiR.repository;

import com.pruebasUnitariasApiR.model.Empleado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@DataJpaTest es una anotación de Spring Boot utilizada en pruebas unitarias para configurar
// una parte específica del contexto de la aplicación centrada en la capa de acceso a datos (JPA)
//es decir, configura solo los repositorios y las entidades. No carga componentes innecesarios como controladores o servicios.
public class EmpleadoRepositoryTest {

    @Autowired
    private EmpleadoRepo repository;

    private Empleado empleado;

    @BeforeEach
    void setup(){
        empleado= new Empleado("Gustavo","Acosta","acosta@gmail.com");
    }

    @DisplayName("Test para guardar un Empleado")
    @Test
    void testGuardarEmpleado(){
        //given- condición previa o configuración
        Empleado empleado1= new Empleado("Juan","Perez","p12@gmail.com");
        //when- ejecución de la prueba
        Empleado empleadoGuardado = repository.save(empleado1);
        //then- resultado esperado
        assertThat(empleadoGuardado).isNotNull();
        assertThat(empleadoGuardado.getId()).isGreaterThan(0);

    }

    @DisplayName("Test para listar los Empleado")
    @Test
    void testListarEmpleados(){
        //given
        Empleado empleado1= new Empleado("Alberto","Torres","ejemplo@gmail.com");
        repository.save(empleado1);
        repository.save(empleado);
        //when
        List<Empleado> listaEmpleados= repository.findAll();
        //then
        assertThat(listaEmpleados).isNotNull();
        assertThat(listaEmpleados.size()).isEqualTo(2);

    }

    @DisplayName("Test para buscar un Empleado por Id")
    @Test
    void testBuscarEmpleadoPorId(){
        //given
        repository.save(empleado);
        //when
        Empleado empleadoBuscado= repository.findById(empleado.getId()).get();
        //then
        assertThat(empleadoBuscado).isNotNull();


    }

    @DisplayName("Test para actualizar un Empleado")
    @Test
    void testActualizarEmpleado(){

        //given
        repository.save(empleado);
        //when
        Empleado empleadoGuardado= repository.findById(empleado.getId()).get();

        empleadoGuardado.setNombre("Agustin");
        empleadoGuardado.setApellido("Alvarez");
        empleadoGuardado.setEmail("qweqw@gmail.com");
        Empleado empleadoActualizado = repository.save(empleadoGuardado);
        //then
        assertThat(empleadoActualizado).isNotNull();
        assertThat(empleadoActualizado.getId()).isEqualTo(empleadoGuardado.getId());
        assertThat(empleadoActualizado.getNombre()).isEqualTo("Agustin");
        assertThat(empleadoActualizado.getApellido()).isEqualTo("Alvarez");
        assertThat(empleadoActualizado.getEmail()).isEqualTo("qweqw@gmail.com");

    }

    @DisplayName("Test para eliminar un Empleado")
    @Test
    void testElimiarEmpleado(){
        //given
        repository.save(empleado);
        //when
        repository.deleteById(empleado.getId());
        Optional<Empleado> empleadoOptional= repository.findById(empleado.getId());
        //then
        assertThat(repository.existsById(empleado.getId())).isFalse();
        assertThat(empleadoOptional).isEmpty();
    }
}
