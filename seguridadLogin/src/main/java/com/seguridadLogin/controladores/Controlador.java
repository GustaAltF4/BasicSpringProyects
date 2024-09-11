package com.seguridadLogin.controladores;

import com.seguridadLogin.entidades.Usuario;
import com.seguridadLogin.servicio.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Controlador {

    @Autowired
    private UsuarioService service;


    @GetMapping("/login")
    public String mostrarLogin() {

        return "login";
    }


    @GetMapping("/home")
    public String mostrarHome(Model modelo) {
        Authentication au= SecurityContextHolder.getContext().getAuthentication();
        if (au.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"))){
            modelo.addAttribute("adminLink", "/admin");
        }
        return "home";
    }

    @GetMapping("/admin")
    public String mostrarAdmin(){
        return "admin";
    }

    @GetMapping("/registrar")
    public String mostrarFormularioDeRegistrar(Model model){
        model.addAttribute("usuario", new Usuario());
        return "registrar";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult result){
        // Validar que las contraseñas coincidan
        if (!usuario.getPassword().equals(usuario.getConfirmPassword())){
            result.rejectValue("confirmPassword", null, "Las Contraseñas no coinciden");
            return "registrar";
        }
        // Validar si el usuario ya existe
        if (service.buscarPorNombre(usuario.getUsername())!=null){
            result.rejectValue("username", null, "El nombre de usuario ya existe");
            return "registrar";
        }
        //guardar el usuario
        service.guardar(usuario);
        return "redirect:/login";
    }

}
