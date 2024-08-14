package com.example.pokeappi.presenter;

import com.example.pokeappi.model.Pokemon;
import com.example.pokeappi.model.PokemonDetails;
import com.example.pokeappi.model.PokemonResponse;
import com.example.pokeappi.network.PokemonApi;
import com.example.pokeappi.view.PokemonView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonPresenter {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private PokemonView view;
    private PokemonApi pokemonApi; // Moved initialization to constructor

    public PokemonPresenter(PokemonView view) {
        this.view = view;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokemonApi = retrofit.create(PokemonApi.class); // Initialize PokemonApi here
    }

    public void getPokemonData() {
        Call<PokemonResponse> call = pokemonApi.getPokemonList();

        call.enqueue(new retrofit2.Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, retrofit2.Response<PokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    List<Pokemon> pokemonList = response.body().getResults();
                    view.showPokemonList(pokemonList);

                    for (Pokemon pokemon : pokemonList) {
                        Call<PokemonDetails> detailsCall = pokemonApi.getPokemonDetails(pokemon.getName());

                        detailsCall.enqueue(new retrofit2.Callback<PokemonDetails>() {
                            @Override
                            public void onResponse(Call<PokemonDetails> call, retrofit2.Response<PokemonDetails> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    String imageUrl = response.body().getSprites().getFrontDefault();
                                    pokemon.setImageUrl(imageUrl);
                                    view.showPokemonList(pokemonList);
                                }
                            }

                            @Override
                            public void onFailure(Call<PokemonDetails> call, Throwable t) {
                                view.showError(t.getMessage());
                            }
                        });
                    }

                } else {
                    view.showError("Error al obtener los datos");
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }

    public void getPokemonDetails(String pokemonName) {
        Call<PokemonDetails> call = pokemonApi.getPokemonDetails(pokemonName);

        call.enqueue(new retrofit2.Callback<PokemonDetails>() {
            @Override
            public void onResponse(Call<PokemonDetails> call, retrofit2.Response<PokemonDetails> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Handle additional logic for details if needed
                }
            }

            @Override
            public void onFailure(Call<PokemonDetails> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }
}
