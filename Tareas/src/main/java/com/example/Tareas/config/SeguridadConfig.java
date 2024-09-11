package com.example.Tareas.config;

import com.example.Tareas.servicios.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize->
                        authorize.requestMatchers("/tareas/**").authenticated()
                                .anyRequest().permitAll()
                ).formLogin(form->form.loginPage("/login").permitAll()
                ).logout(LogoutConfigurer::permitAll)
                .csrf(c-> c.disable())// Deshabilitar CSRF para la consola H2 (solo para desarrollo)
                .headers(h-> h.frameOptions(f-> f.disable()));;


        return http.build();

    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new CustomUserDetailsService();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // No encriptar la contraseña
    }
}
