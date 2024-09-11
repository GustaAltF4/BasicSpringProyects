package com.blog.demo.controlador;

import com.blog.demo.dto.LoginDto;
import com.blog.demo.dto.RegistroDto;
import com.blog.demo.entidades.Rol;
import com.blog.demo.entidades.Usuario;
import com.blog.demo.repositorio.RolRepositorio;
import com.blog.demo.repositorio.UsuarioRepositorio;
import com.blog.demo.seguridad.JwtAuthResponseDto;
import com.blog.demo.seguridad.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthControlador {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private RolRepositorio rolRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping("/iniciarSesion")
    public ResponseEntity<JwtAuthResponseDto> authenticateUser(@RequestBody LoginDto loginDto) {


        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail()
                , loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //obtengo el token del jwtProvider

        String token= jwtTokenProvider.generarToken(authentication);

        return ResponseEntity.ok( new JwtAuthResponseDto(token));

    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDto registroDto) {
        if (usuarioRepositorio.existsByUsername(registroDto.getUsername())) {
            return new ResponseEntity<>("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        if (usuarioRepositorio.existsByEmail(registroDto.getEmail())) {
            return new ResponseEntity<>("El Email de usuario ya esta en uso", HttpStatus.BAD_REQUEST);
        }

        Usuario usuario= new Usuario();
        usuario.setNombre(registroDto.getNombre());
        usuario.setUsername(registroDto.getUsername());
        usuario.setEmail(registroDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));

        Rol roles= rolRepositorio.findByNombre("ROLE_ADMIN").get();
        usuario.setRoles(Collections.singleton(roles));

        usuarioRepositorio.save(usuario);
        return new ResponseEntity<>("Usuario registrado con exito", HttpStatus.OK);


    }



}
