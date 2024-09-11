package com.blog.demo.excepciones;

import org.springframework.http.HttpStatus;

public class BlogAppException extends RuntimeException {

    private HttpStatus estado;
    private String mensaje;

    public BlogAppException(String mensaje, HttpStatus estado) {
        this.mensaje = mensaje;
        this.estado = estado;
    }

    public BlogAppException(HttpStatus estado, String mensaje, String mensaje1) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.mensaje = mensaje1;
    }

    public HttpStatus getEstado() {
        return estado;
    }

    public void setEstado(HttpStatus estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
