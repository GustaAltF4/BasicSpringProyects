package com.poke.controller;

import com.poke.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WebController {

    @Autowired
    private RestTemplate restTemplate ;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping ("/buscar")
    public String buscarPokemon(@RequestParam(name="nombre", required = false, defaultValue = "")String nombre, Model model) {
        if(!nombre.isEmpty() ){
            String url=  "https://pokeapi.co/api/v2/pokemon/" + nombre.toLowerCase();
            try{
                Pokemon poke= restTemplate.getForObject(url, Pokemon.class);
                model.addAttribute("pokemon", poke);
            }catch (Exception e){
                model.addAttribute("error", "No se encontró el Pokémon");
            }


        }
        return "index";
    }

}
