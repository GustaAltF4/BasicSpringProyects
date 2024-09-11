package com.seguridadLogin.configuracion;

import com.seguridadLogin.servicio.UserDetailsServicePersonalizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadConfig {

    @Autowired
    private UserDetailsServicePersonalizado userDetailsService1;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public void authenticate(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(userDetailsService1).passwordEncoder(passwordEncoder);

    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests(au -> au
                        .requestMatchers("/login", "/registrar", "/resources/**").permitAll() // Permitir acceso a la página de login y recursos estáticos
                        .requestMatchers("/admin/**", "/h2-console/**").hasRole("ADMIN") // Acceso solo para usuarios con rol de ADMIN
                        .anyRequest().authenticated()) // Requiere autenticación para cualquier otra ruta
                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll())
                .logout(log -> log.logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout").permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(HeadersConfigurer::disable);


        return http.build();
    }

}
