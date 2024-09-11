package com.pruebasUnitarias.services.impl;

import com.pruebasUnitarias.services.ServicioA;
import com.pruebasUnitarias.services.ServicioB;

public class ServicioBImpl implements ServicioB {

    public ServicioA servicioA;


    @Override
    public ServicioA getServicioA() {
        return servicioA;
    }

    @Override
    public void setServicioA(ServicioA servicioA) {
        this.servicioA= servicioA;

    }

    @Override
    public int multiplicarSumar(int a, int b, int multiplicador) {
        return servicioA.sumar(a,b)* multiplicador;
    }

    @Override
    public int multiplicar(int a, int b) {
        return a*b;
    }
}
