package me.ulises.PokeApiTechnicalTest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(PokemonService.class);

    private static String POKEAPI_URI = "https://pokeapi.co/api/v2";
    private static String POKEAPI_SPRITE_URI_BASE = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

    @Transactional()
    public Pokemon findAndSave(String id) {
        String uri = POKEAPI_URI + "/pokemon/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Pokemon result = restTemplate.getForObject(uri, Pokemon.class);
        logger.info("fetching pokemon from: " + uri);
        if (pokemonRepository.findById(result.getId()).isPresent()) {
            logger.info("pokemon already exists in database, returning it...");
            result = pokemonRepository.findById(result.id).get();
        } else {
            logger.info("pokemon not found in database, saving...");
            result.setSprite(POKEAPI_SPRITE_URI_BASE + result.getId() + ".png");
            pokemonRepository.save(result);
            logger.info("pokemon saved");
            logger.info("saving pokemon abilities");
            for (PokemonAbility ability : result.getAbilities()) {
                if (abilityRepository.findById(ability.getAbility().name).isPresent()) {
                    logger.info("ability already exists in database, fetching...");
                    ability.setAbility(abilityRepository.findById(ability.getAbility().name).get());
                } else {
                    logger.info("ability not found in database, saving...");
                    ability.setAbility(abilityRepository.save(ability.getAbility()));
                }
                if (pokemonAbilityRepository.findByPokemonAndAbility(result.getId(), ability.getAbility().getName()) == null) {
                    logger.info("saving pokemon ability relationship...");
                    ability.setPokemon(result);
                    ability.setAbility(ability.getAbility());
                    pokemonAbilityRepository.save(ability);
                } else {
                    logger.info("pokemon ability relationship already exists in database, fetching...");
                    ability = pokemonAbilityRepository.findByPokemonAndAbility(result.getId(), ability.getAbility().getName());
                }
            }
            logger.info("pokemon abilities saved");
        }
        return result;
    }
    
}
