package me.ulises.PokeApiTechnicalTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.ulises.PokeApiTechnicalTest.entity.Ability;

public interface IAbilityRepository extends JpaRepository<Ability, String> {
    
}
