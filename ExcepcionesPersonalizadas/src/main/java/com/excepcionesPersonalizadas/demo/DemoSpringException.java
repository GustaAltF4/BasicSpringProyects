package com.excepcionesPersonalizadas.demo;

public class DemoSpringException extends Exception{

    private ExceptionDetails details;

    public DemoSpringException(String message, ExceptionDetails details, Throwable e) {
        super(message,e);
        setDetails(details);
    }

    public DemoSpringException(String message, ExceptionDetails details) {
        super(message);
        setDetails(details);
    }


    public ExceptionDetails getDetails() {
        return details;
    }

    public void setDetails(ExceptionDetails details) {
        this.details = details;
    }
}
