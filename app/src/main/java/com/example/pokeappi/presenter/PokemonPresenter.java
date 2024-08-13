package com.example.pokeappi.presenter;

import com.example.pokeappi.model.PokemonResponse;
import com.example.pokeappi.network.PokemonApi;
import com.example.pokeappi.view.PokemonView;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonPresenter {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private PokemonView view;

    public PokemonPresenter(PokemonView view) {
        this.view = view;
    }

    public void getPokemonData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonApi pokemonApi = retrofit.create(PokemonApi.class);
        Call<PokemonResponse> call = pokemonApi.getPokemonList();

        call.enqueue(new retrofit2.Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, retrofit2.Response<PokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.showPokemonList(response.body().getResults());
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
}
