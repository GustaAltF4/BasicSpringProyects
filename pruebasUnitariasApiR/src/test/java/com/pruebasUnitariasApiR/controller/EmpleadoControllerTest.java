package com.pruebasUnitariasApiR.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebasUnitariasApiR.model.Empleado;
import com.pruebasUnitariasApiR.service.EmpleadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;



@WebMvcTest //anotación para pruebas a controllers
public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGuardarEmpleado() throws Exception {
        //given
        Empleado empleado= new Empleado(1L,"Gustavo","Acosta","acosta@gmail.com");
        given(service.saveEmpleado(any(Empleado.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));
        //when
        ResultActions response= mockMvc.perform(post("/api/empleados")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empleado)));
        //then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is(empleado.getNombre())))
                .andExpect(jsonPath("$.apellido", is(empleado.getApellido())))
                .andExpect(jsonPath("$.email", is(empleado.getEmail())));
    }

    @Test
    void testListarEmpleados() throws Exception{
        //given
        List<Empleado> listaEmpleados= new ArrayList<>();
        listaEmpleados.add(new Empleado(1L,"Gustavo","Acosta","acosta@gmail.com"));
        listaEmpleados.add(new Empleado(2L,"Nico","Acosta","acosta2@gmail.com"));
        listaEmpleados.add(new Empleado(3L,"Lucas","Acosta","acosta@gmail.com"));
        listaEmpleados.add(new Empleado(4L,"Alfredo","Acosta","acosta@gmail.com"));
        listaEmpleados.add(new Empleado(5L,"Viviana","Salomon","salomon@gmail.com"));

        given(service.getAllEmpleados()).willReturn(listaEmpleados);
        //when
        ResultActions response= mockMvc.perform(get("/api/empleados"));
        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect( jsonPath("$.size()",is(listaEmpleados.size())));

    }

    @Test
    void testObtenerEmpleadoPorId() throws Exception{
        //given
        long empleadoId=1L;
        Empleado empleado=new Empleado(3L,"Lucas","Acosta","acosta@gmail.com");

        given(service.getEmpleadoById(empleadoId)).willReturn(Optional.of(empleado));
        //when
        ResultActions response= mockMvc.perform(get("/api/empleados/{id}",empleadoId));
        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nombre",is(empleado.getNombre())))
                .andExpect(jsonPath("$.apellido", is(empleado.getApellido())))
                .andExpect(jsonPath("$.email", is(empleado.getEmail())));
    }

    @Test
    void testObtenerEmpleadoNoEncontrado() throws Exception{
        //given
        long empleadoId=1L;
        Empleado empleado=new Empleado(3L,"Lucas","Acosta","acosta@gmail.com");

        given(service.getEmpleadoById(empleadoId)).willReturn(Optional.empty());
        //when
        ResultActions response= mockMvc.perform(get("/api/empleados/{id}",empleadoId));
        //then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testActualizarEmpleado() throws Exception{

        //given
        long empleadoId=1L;
        Empleado empleadoGuardado=new Empleado("Lucas","Acosta","acosta@gmail.com");
        Empleado empleadoActualizado=new Empleado("Nicolas","Ramirez","asdasda@gmail.com");

        given(service.getEmpleadoById(empleadoId)).willReturn(Optional.of(empleadoGuardado));
        given(service.updateEmpleado(any(Empleado.class)))
                .willAnswer((invocation)->invocation.getArgument(0));
        //when
        ResultActions response= mockMvc.perform(put("/api/empleados/{id}",empleadoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empleadoActualizado)));
        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nombre",is(empleadoActualizado.getNombre())))
                .andExpect(jsonPath("$.apellido", is(empleadoActualizado.getApellido())))
                .andExpect(jsonPath("$.email", is(empleadoActualizado.getEmail())));
    }

    @Test
    void testActualizarEmpleadoNoEncontrado() throws Exception{

        //given
        long empleadoId=1L;
        Empleado empleadoGuardado=new Empleado("Lucas","Acosta","acosta@gmail.com");
        Empleado empleadoActualizado=new Empleado("Nicolas","Ramirez","asdasda@gmail.com");

        given(service.getEmpleadoById(empleadoId)).willReturn(Optional.empty());
        given(service.updateEmpleado(any(Empleado.class)))
                .willAnswer((invocation)->invocation.getArgument(0));
        //when
        ResultActions response= mockMvc.perform(put("/api/empleados/{id}",empleadoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empleadoActualizado)));
        //then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testEliminarEmpleado()throws Exception{
        //given
        long empleadoId=1L;
        willDoNothing().given(service).deleteEmpleado(empleadoId);
        //when
        ResultActions response= mockMvc.perform(delete("/api/empleados/{id}",empleadoId));
        //then
        response.andExpect(status().isOk())
                .andDo(print());
    }



}
