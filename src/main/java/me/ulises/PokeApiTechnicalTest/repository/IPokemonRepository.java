package me.ulises.PokeApiTechnicalTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.ulises.PokeApiTechnicalTest.entity.Pokemon;

public interface IPokemonRepository extends JpaRepository<Pokemon, Long> {
    
}
