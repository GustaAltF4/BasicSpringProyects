package com.blog.demo.seguridad;

import com.blog.demo.excepciones.BlogAppException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generarToken(Authentication authentication){
        String username= authentication.getName();
        Date fechaActual= new Date();
        Date fechaExpiracion= new Date(fechaActual.getTime()+jwtExpirationInMs);

        String token= Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(fechaExpiracion).signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
        return token;
    }

    public String obtenerUsernameDelJwt(String token){
        Claims claims= Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validarToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;

        }catch (SignatureException ex){
            throw new BlogAppException("Firma jwt no valida", HttpStatus.BAD_REQUEST);
        }catch (MalformedJwtException ex){
            throw new BlogAppException("Token jwt no valida", HttpStatus.BAD_REQUEST);
        }catch (ExpiredJwtException ex){
            throw new BlogAppException("Token jwt caducado", HttpStatus.BAD_REQUEST);
        }catch (UnsupportedJwtException ex){
            throw new BlogAppException("Token jwt no compatible", HttpStatus.BAD_REQUEST);
        }catch (IllegalArgumentException ex){
            throw new BlogAppException("La cadena claims Jwt esta vaciá", HttpStatus.BAD_REQUEST);
        }

    }

}
