package com.miapp.controller;


import com.miapp.model.Productos;
import com.miapp.service.ProductoService;
import com.miapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("productos", productoService.getAllProductos());
        return "index"; // Esta es la vista Thymeleaf
    }

    @GetMapping("/producto/{id}")
    public String productoDetail(@PathVariable Integer id, Model model) {
        Productos producto = productoService.getProductoById(id);
        model.addAttribute("producto", producto);
        return "producto"; // Vista Thymeleaf para detalles del producto
    }
//    @GetMapping("/")
//    public String index() {
//        return "index";
//    }
    @GetMapping("/pecho")
    public String pecho(Model model) {
        model.addAttribute("productos", productoService.getProductosByCategoria("pecho"));
        return "pecho";
    }
    @GetMapping("/cola_ligas")
    public String colaLigas(Model model) {
        model.addAttribute("productos",productoService.getProductosByCategoria("cola_ligas"));
        return "cola_ligas";
    }

    @GetMapping("/cuerpo_completo")
    public String cuerpoCompleto(Model model) {
        model.addAttribute("productos",productoService.getProductosByCategoria("cuerpoCompl_Conjuntos"));
        return "cuerpoCompleto";
    }

    @GetMapping("/gargantillas")
    public String gargantillas(Model model) {
        model.addAttribute("productos",productoService.getProductosByCategoria("gargantillas"));
        return "gargantillas";
    }

    @GetMapping("/hombre")
    public String hombre(Model model) {
        model.addAttribute("productos",productoService.getProductosByCategoria("hombre"));
        return "hombre";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }




}
