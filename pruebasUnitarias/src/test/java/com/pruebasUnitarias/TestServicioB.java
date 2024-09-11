package com.pruebasUnitarias;

import com.pruebasUnitarias.services.ServicioA;
import com.pruebasUnitarias.services.ServicioB;
import com.pruebasUnitarias.services.impl.ServicioAImpl;
import com.pruebasUnitarias.services.impl.ServicioBImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestServicioB {

    @Test
    public void testMultiplicar(){
        ServicioB servicioB= new ServicioBImpl();
        assertEquals(servicioB.multiplicar(2,3),6);
    }

    @Test
    public void testMultiplicarSumar(){
        ServicioA servicioA= new ServicioAImpl();
        ServicioB servicioB= new ServicioBImpl();
        servicioB.setServicioA(servicioA);

        Assertions.assertEquals(servicioB.multiplicarSumar(2,3,2),10);
    }
    @Test
    public void testMultiplicarSumarMockito(){
        ServicioA servicioA= Mockito.mock(ServicioA.class);
        Mockito.when(servicioA.sumar(2,3)).thenReturn(5);
        ServicioB servicioB= new ServicioBImpl();
        servicioB.setServicioA(servicioA);

        Assertions.assertEquals(servicioB.multiplicarSumar(2,3,2),10);
    }
}
