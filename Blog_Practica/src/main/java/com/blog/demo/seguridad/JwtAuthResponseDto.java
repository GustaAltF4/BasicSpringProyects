package com.blog.demo.seguridad;

public class JwtAuthResponseDto {

    private String tokenDeAcceso;
    private String tipoDeToken = "Bearer";

    public JwtAuthResponseDto(String tokenDeAcceso) {
        this.tokenDeAcceso = tokenDeAcceso;
    }

    public JwtAuthResponseDto(String tipoDeToken, String tokenDeAcceso) {
        this.tipoDeToken = tipoDeToken;
        this.tokenDeAcceso = tokenDeAcceso;
    }

    public String getTokenDeAcceso() {
        return tokenDeAcceso;
    }

    public void setTokenDeAcceso(String tokenDeAcceso) {
        this.tokenDeAcceso = tokenDeAcceso;
    }

    public String getTipoDeToken() {
        return tipoDeToken;
    }

    public void setTipoDeToken(String tipoDeToken) {
        this.tipoDeToken = tipoDeToken;
    }
}
