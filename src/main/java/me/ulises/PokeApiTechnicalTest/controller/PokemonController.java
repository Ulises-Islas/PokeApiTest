package me.ulises.PokeApiTechnicalTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.ulises.PokeApiTechnicalTest.service.PokemonService;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService service;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findAndSave(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(service.findAndSave(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred", HttpStatus.BAD_REQUEST);
        }
    }
    
}
