package me.ulises.PokeApiTechnicalTest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pokemon_ability")
public class PokemonAbility {
    
    @Id
    @ManyToOne()
    @JoinColumn(name = "pokemon", nullable = false)
    private Pokemon pokemon;

    @Id
    @ManyToOne
    @JoinColumn(name = "ability", nullable = false)
    private Ability ability;

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

}
