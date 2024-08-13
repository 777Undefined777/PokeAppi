package com.example.pokeappi.view;

import com.example.pokeappi.model.Pokemon;

import java.util.List;

public interface PokemonView {
    void showPokemonList(List<Pokemon> pokemonList);
    void showError(String message);
}
