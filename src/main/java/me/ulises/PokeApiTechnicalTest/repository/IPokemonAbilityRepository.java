package me.ulises.PokeApiTechnicalTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.ulises.PokeApiTechnicalTest.entity.PokemonAbility;

public interface IPokemonAbilityRepository extends JpaRepository<PokemonAbility, Long> {

    @Query(value = "SELECT * FROM pokemon_ability WHERE pokemon_id = :pokemon AND ability_id = :ability", nativeQuery = true)
    PokemonAbility findByPokemonAndAbility(@Param("pokemon") Long pokemon, @Param("ability") String ability);
    
}
