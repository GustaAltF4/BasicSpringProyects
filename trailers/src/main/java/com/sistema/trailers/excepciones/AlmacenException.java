package com.sistema.trailers.excepciones;



public class AlmacenException extends RuntimeException {

    public AlmacenException(String mensaje) {
        super(mensaje);
    }
    public AlmacenException(String message, Throwable exception) {
        super(message, exception);
    }
}
