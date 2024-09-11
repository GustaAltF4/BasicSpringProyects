package com.example.Tareas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LonginController {

    @GetMapping("/login")public String showLoginForm() {
        return"login"; // Muestra el formulario de inicio de sesión
    }
}
