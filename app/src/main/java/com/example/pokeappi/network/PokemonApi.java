package com.example.pokeappi.network;

import com.example.pokeappi.model.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonApi {
    @GET("pokemon")
    Call<PokemonResponse> getPokemonList();
}