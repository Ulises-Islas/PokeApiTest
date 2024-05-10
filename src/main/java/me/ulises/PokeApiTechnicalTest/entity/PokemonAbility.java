package me.ulises.PokeApiTechnicalTest.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pokemon_ability")
public class PokemonAbility implements Serializable {
    
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pokemon_id", nullable = false)
    private Pokemon pokemon;

    @ManyToOne
    @JoinColumn(name = "ability_id", nullable = false)
    private Ability ability;

    @Column(name = "is_hidden", nullable = false)
    @JsonProperty(value = "is_hidden")
    private boolean isHidden;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isIs_hidden() {
        return isHidden;
    }

    public void setIs_hidden(boolean is_hidden) {
        this.isHidden = is_hidden;
    }

}
