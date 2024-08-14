package com.example.pokeappi.network;


import com.example.pokeappi.model.PokemonResponse;

import com.example.pokeappi.model.PokemonDetails;
import com.example.pokeappi.model.PokemonResponse;
import retrofit2.http.Path;


import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonApi {
    @GET("pokemon")
    Call<PokemonResponse> getPokemonList();



    @GET("pokemon/{name}")
    Call<PokemonDetails> getPokemonDetails(@Path("name") String name);  // Agrega esta línea

}