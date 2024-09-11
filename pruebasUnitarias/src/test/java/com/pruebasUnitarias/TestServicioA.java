package com.pruebasUnitarias;

import com.pruebasUnitarias.services.ServicioA;
import com.pruebasUnitarias.services.impl.ServicioAImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;


public class TestServicioA {

    @Test
    public void testSumar() {
        int a=2;
        int b=2;
        ServicioA servicioA= new ServicioAImpl();

        assertEquals(servicioA.sumar(a,b),4);
    }

}
