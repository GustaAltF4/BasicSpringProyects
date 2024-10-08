package com.blog.demo.dto;

import java.util.Date;

public class ErrorDetalles {
    private Date marcaDeTiempo;
    private String mensaje;
    private String detalles;

    public ErrorDetalles(Date marcaDeTiempo, String detalles, String mensaje) {
        this.marcaDeTiempo = marcaDeTiempo;
        this.detalles = detalles;
        this.mensaje = mensaje;
    }

    public Date getMarcaDeTiempo() {
        return marcaDeTiempo;
    }

    public void setMarcaDeTiempo(Date marcaDeTiempo) {
        this.marcaDeTiempo = marcaDeTiempo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
