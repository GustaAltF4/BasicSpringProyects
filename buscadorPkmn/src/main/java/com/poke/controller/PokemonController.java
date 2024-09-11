package com.poke.controller;

import com.poke.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    @Autowired
    private RestTemplate restTemplate;
    //Puedes usar RestTemplate para hacer solicitudes HTTP a servicios externos.
    //RestTemplate es una herramienta poderosa y flexible para interactuar con servicios web
    //en aplicaciones Spring Boot, facilitando la integración con APIs RESTful.

    @GetMapping("/{nombre}")
    public ResponseEntity<Pokemon> buscarPokemon(@PathVariable String nombre) {
        String url=  "https://pokeapi.co/api/v2/pokemon/" + nombre;
        Pokemon poke= restTemplate.getForObject(url, Pokemon.class);
        return ResponseEntity.ok(poke);

    }

}
