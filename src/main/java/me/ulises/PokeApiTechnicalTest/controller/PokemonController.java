package me.ulises.PokeApiTechnicalTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.ulises.PokeApiTechnicalTest.entity.Pokemon;
import me.ulises.PokeApiTechnicalTest.service.PokemonService;

@Tag(name = "Pokemon", description = "Pokemon API")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService service;

    @Operation(summary = "Find and save a pokemon by id", description = "Find a pokemon by id and save it in the database if it doesn't exist yet")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pokemon found and saved"),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> findAndSave(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(service.findAndSave(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}
