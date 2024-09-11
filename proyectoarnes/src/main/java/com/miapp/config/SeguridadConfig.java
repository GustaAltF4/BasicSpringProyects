package com.miapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
public class SeguridadConfig  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception{
        httpSec
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(autorizar -> autorizar
                        .requestMatchers("/api/productos/**").hasRole("ADMIN")
                        .requestMatchers("/api/productosver/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().permitAll()
                ).httpBasic(Customizer.withDefaults());


        return httpSec.build();
    }


    @Bean
    public PasswordEncoder contraseniaEncriptada(){
        return new BCryptPasswordEncoder();
    }


}
