package me.ulises.PokeApiTechnicalTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.ulises.PokeApiTechnicalTest.entity.Pokemon;

@SpringBootTest
public class PokemonSeriveTesting {

    @Autowired
    PokemonService pokemonService;

    @Test
    @DisplayName("Test find Pikachu")
    public void testFindPikachu() {
        Pokemon pikachu = pokemonService.findAndSave("pikachu");
        assertEquals("pikachu", pikachu.getName());
    }

    @Test
    @DisplayName("Test should not find a pokemon")
    public void testFindNonExistentPokemon() {
        Pokemon pokemon = pokemonService.findAndSave("nonExistentPokemon");
        assertEquals(null, pokemon);
    }

    @Test
    @DisplayName("Test find bulbasaur")
    public void testFindBulbasaur() {
        Pokemon bulbasaur = pokemonService.findAndSave("1");
        assertEquals("bulbasaur", bulbasaur.getName());
    }
    
}
