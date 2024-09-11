package com.excepcionesPersonalizadas.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

@SpringBootApplication
public class ExcepcionesPersonalizadasApplication {

    public static void main(String[] args) throws DemoSpringException {

        if (ObjectUtils.isEmpty(args)) {
            throw new DemoSpringException("Mensaje q queremos mostrar en el log",
                    new ExceptionDetails("Error", "Mensaje q queremos mostrar al user"));
        }

        try{

        }catch (Exception e){
            throw new DemoSpringException("Mensaje para log de Error",
                    new ExceptionDetails("warning", "Mensaje de error para el user")
                    ,e); //3.er constructor que pasa la excepción util para cuando queremos mostrar el error en el log
        }


        //SpringApplication.run(ExcepcionesPersonalizadasApplication.class, args);
    }

}
