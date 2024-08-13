package com.example.pokeappi.network;

<<<<<<< HEAD
import com.example.pokeappi.model.PokemonResponse;
=======
import com.example.pokeappi.model.PokemonDetails;
import com.example.pokeappi.model.PokemonResponse;
import retrofit2.http.Path;

>>>>>>> 604e6cf (TRaida de imagenes con glide)

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonApi {
    @GET("pokemon")
    Call<PokemonResponse> getPokemonList();
<<<<<<< HEAD
=======


    @GET("pokemon/{name}")
    Call<PokemonDetails> getPokemonDetails(@Path("name") String name);  // Agrega esta línea
>>>>>>> 604e6cf (TRaida de imagenes con glide)
}