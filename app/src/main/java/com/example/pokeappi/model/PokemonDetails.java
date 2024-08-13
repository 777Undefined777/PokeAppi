package com.example.pokeappi.model;

public class PokemonDetails {
    private Sprites sprites;

    public Sprites getSprites() {
        return sprites;
    }

    public class Sprites {
        private String front_default;

        public String getFrontDefault() {
            return front_default;
        }
    }
}
