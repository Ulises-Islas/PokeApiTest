package me.ulises.PokeApiTechnicalTest.entity;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pokemon")
public class Pokemon implements Serializable {
    
    @Id
    public Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "height", nullable = false)
    private int height;

    @Column(name = "is_default", nullable = false)
    @JsonProperty(value = "is_default")
    private boolean isDefault;

    @OneToMany(mappedBy = "pokemon")
    private Set<PokemonAbility> abilities;

    @Column(name = "sprite", nullable = false)
    private String sprite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Set<PokemonAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(Set<PokemonAbility> abilities) {
        this.abilities = abilities;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

}
