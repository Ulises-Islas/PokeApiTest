package me.ulises.PokeApiTechnicalTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import me.ulises.PokeApiTechnicalTest.entity.Pokemon;
import me.ulises.PokeApiTechnicalTest.entity.PokemonAbility;
import me.ulises.PokeApiTechnicalTest.repository.IAbilityRepository;
import me.ulises.PokeApiTechnicalTest.repository.IPokemonAbilityRepository;
import me.ulises.PokeApiTechnicalTest.repository.IPokemonRepository;

@Service
public class PokemonService {

    @Autowired
    private IPokemonRepository pokemonRepository;
    @Autowired
    private IAbilityRepository abilityRepository;
    @Autowired
    private IPokemonAbilityRepository pokemonAbilityRepository;

    private static String POKEAPI_URI = "https://pokeapi.co/api/v2";
    private static String POKEAPI_SPRITE_URI_BASE = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

    @Transactional()
    public Pokemon findAndSave(String id) {
        String uri = POKEAPI_URI + "/pokemon/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Pokemon result = restTemplate.getForObject(uri, Pokemon.class);
        if (pokemonRepository.findById(result.getId()).isPresent()) {
            result = pokemonRepository.findById(result.id).get();
        } else {
            result.setSprite(POKEAPI_SPRITE_URI_BASE + result.getId() + ".png");
            pokemonRepository.save(result);
            for (PokemonAbility ability : result.getAbilities()) {
                if (abilityRepository.findById(ability.getAbility().name).isPresent()) {
                    ability.setAbility(abilityRepository.findById(ability.getAbility().name).get());
                } else {
                    ability.setAbility(abilityRepository.save(ability.getAbility()));
                }
                if (pokemonAbilityRepository.findByPokemonAndAbility(result.getId(), ability.getAbility().getName()) == null) {
                    ability.setPokemon(result);
                    ability.setAbility(ability.getAbility());
                    pokemonAbilityRepository.save(ability);
                } else {
                    ability = pokemonAbilityRepository.findByPokemonAndAbility(result.getId(), ability.getAbility().getName());
                }
            }
        }
        return result;
    }
    
}
