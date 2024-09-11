package com.pruebasUnitariasApiR.service;

import com.pruebasUnitariasApiR.exception.ResourceNotFoundException;
import com.pruebasUnitariasApiR.model.Empleado;
import com.pruebasUnitariasApiR.repository.EmpleadoRepo;
import com.pruebasUnitariasApiR.service.impl.EmpleadoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

/*Las anotaciones @Mock y @InjectMocks se utilizan comúnmente en pruebas unitarias
con Mockito, una biblioteca popular en Java para crear mocks (objetos simulados)
 que emulan el comportamiento de dependencias en un entorno controlado.*/

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTest {

    /*@Mock
    Propósito: Se utiliza para crear un mock (un objeto simulado) de una clase o interfaz.
    Este mock reemplaza el comportamiento real del objeto con un comportamiento simulado,
    permitiendo controlar y verificar cómo interactúan otros componentes con este objeto durante las pruebas.

    Cómo funciona: Al marcar un campo con @Mock, Mockito creará un objeto simulado para ese campo.
    Este objeto no ejecutará el comportamiento real de la clase, sino que solo devolverá valores simulados
    o lo que se configure en la prueba.*/

    @Mock
    private EmpleadoRepo repository;

    /*@InjectMocks
    Propósito: Se utiliza para inyectar mocks en una instancia de la clase que estás probando.
     Específicamente, @InjectMocks crea una instancia de la clase bajo prueba e inyecta los mocks correspondientes
     en sus dependencias (generalmente los campos marcados con @Mock).

    Cómo funciona: Cuando se utiliza @InjectMocks, Mockito intenta inyectar los mocks en los campos correspondientes de la clase bajo prueba.
    Esta inyección puede hacerse por constructor, por setter o directamente en los campos (field injection),
     dependiendo de cómo esté estructurada la clase.
     */

    @InjectMocks
    private EmpleadoServiceImpl service;


    private Empleado empleado;
/*
    *   Given: Preparas las condiciones simulando los comportamientos de las dependencias.
    *   When: Ejecutas la acción que deseas probar.
    *   Then: Verificas que el resultado de la acción es el esperado, basado en lo que simulaste en el Given.
*/
    @BeforeEach
    void setup(){
        empleado= new Empleado(1L,"Gustavo","Acosta","acosta@gmail.com");
        //Crea un objeto Empleado que se utilizará en las pruebas.
    }

    @DisplayName("Test para guardar un Empleado")
    @Test
    void testGuardarEmpleado(){
        //given
        given(repository.findByEmail(empleado.getEmail()))
                .willReturn(Optional.empty());// Simula que no existe un empleado con el correo dado en la base de datos.
        given(repository.save(empleado)).willReturn(empleado);// Simula que el método save del repositorio devuelve el empleado que se le pasa como argumento.
        //when
        Empleado empleadoGuardado = service.saveEmpleado(empleado);//Llama al método saveEmpleado del servicio.
        //then
        assertThat(empleadoGuardado).isNotNull();//Verifica que el empleado guardado no es nulo.

    }

    @DisplayName("Test para guardar un Empleado con throws exception" )
    @Test
    void testGuardarEmpleadoConException(){
        //given
        given(repository.findByEmail(empleado.getEmail()))
                .willReturn(Optional.of(empleado));//Simula que ya existe un empleado con ese correo.

        //when
        assertThrows(ResourceNotFoundException.class,()->{
            service.saveEmpleado(empleado);//: Verifica que se lanza una excepción ResourceNotFoundException cuando se intenta guardar un empleado que ya existe.
        });

        //then
        verify(repository, never()).save(any(Empleado.class));//Verifica que el método save del repositorio nunca es llamado en este caso.

    }

    @DisplayName("Test para listar los Empleados")
    @Test
    void testListarEmpleados(){
        //given
        Empleado empleado1= new Empleado(2L,"Alberto","Torres","ejemplo@gmail.com");
        given(repository.findAll()).willReturn(List.of(empleado,empleado1));// Simula que el método findAll devuelve una lista con dos empleados.
        //when
       List<Empleado> listaEmpleados= service.getAllEmpleados();
        //then
        assertThat(listaEmpleados).isNotNull();
        assertThat(listaEmpleados.size()).isEqualTo(2);//Verifica que la lista de empleados no es nula y que contiene dos elementos.
    }

    @DisplayName("Test para retornar una lista vacia")
    @Test
    void listarConlecionEmpleadosVacia(){
        //given
        Empleado empleado1= new Empleado(2L,"Alberto","Torres","ejemplo@gmail.com");
        given(repository.findAll()).willReturn(Collections.emptyList());//Simula que el método findAll devuelve una lista vacía.
        //when
        List<Empleado> lista= service.getAllEmpleados();
        //then
        assertThat(lista).isEmpty();
        assertThat(lista.size()).isEqualTo(0);// Verifican que la lista está vacía.

    }
    @DisplayName("Test para buscar un Empleado por Id")
    @Test
    void testObtenerEmpleadoPorId(){
        //given
        given(repository.findById(1L)).willReturn(Optional.of(empleado));//Simula que el método findById devuelve un empleado con ID 1L.
        //when
        Empleado empleadoBuscado= service.getEmpleadoById(empleado.getId()).get();//Llama al método getEmpleadoById del servicio y obtiene el empleado.
        //then
        assertThat(empleadoBuscado).isNotNull();//Verifica que el empleado devuelto no es nulo.
    }

    @DisplayName("Test para actualizar un Empleado")
    @Test
    void testActualizarEmpleado(){
        //given
        given(repository.save(empleado)).willReturn(empleado);// Simula que el método save del repositorio devuelve el empleado actualizado.
        empleado.setEmail("qweqw@gmail.com");
        empleado.setNombre("Agustin");// Cambia el email y nombre del empleado.
        //when
        Empleado empleadoActualizado= service.updateEmpleado(empleado);//Llama al método updateEmpleado del servicio.
        //then
        assertThat(empleadoActualizado.getEmail()).isEqualTo("qweqw@gmail.com");
        assertThat(empleadoActualizado.getNombre()).isEqualTo("Agustin");//Verifica que el email y nombre del empleado han sido actualizados correctamente.
    }

    @DisplayName("Test para eliminar un Empleado")
    @Test
    void testEliminarEmpleado(){
        //given
        Long idEmpleado=1L;
        willDoNothing().given(repository).deleteById(idEmpleado);//Simula que el método deleteById no hace nada cuando se le pasa el ID del empleado.
        //when
        service.deleteEmpleado(idEmpleado);//Llama al método deleteEmpleado del servicio.
        //then
        verify(repository, times(1)).deleteById(idEmpleado);// Verifica que el método deleteById del repositorio fue llamado una vez.
    }




}
