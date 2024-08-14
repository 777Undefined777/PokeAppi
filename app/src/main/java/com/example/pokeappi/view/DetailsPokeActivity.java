package com.example.pokeappi.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pokeappi.R;
import com.example.pokeappi.model.PokemonDetails;
import com.example.pokeappi.view.FavoritePokeActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class DetailsPokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_poke);

        // Obtener los datos del Intent
        String pokemonName = getIntent().getStringExtra("pokemon_name");
        String pokemonImageUrl = getIntent().getStringExtra("pokemon_image_url");
        PokemonDetails pokemonDetails = (PokemonDetails) getIntent().getSerializableExtra("pokemon_details");

        // Mostrar los datos recibidos en la UI
        TextView nameTextView = findViewById(R.id.details_pokemon_name);
        ImageView imageView = findViewById(R.id.details_pokemon_image);
        ImageView favoriteImageView = findViewById(R.id.favorites_xl);

        nameTextView.setText(pokemonName);
        Glide.with(this).load(pokemonImageUrl).into(imageView);

        // Mostrar los stats
        TextView statsTextView = findViewById(R.id.details_pokemon_stats);
        if (pokemonDetails != null && pokemonDetails.getStats() != null) {
            StringBuilder statsBuilder = new StringBuilder();
            for (PokemonDetails.Stat stat : pokemonDetails.getStats()) {
                statsBuilder.append(stat.getStat().getName())
                        .append(": ")
                        .append(stat.getBaseStat())
                        .append("\n");
            }
            statsTextView.setText(statsBuilder.toString());
        } else {
            statsTextView.setText("No stats available");
        }

        // Configurar el click listener para el ImageView de favoritos
        favoriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("PokeAppiPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Recuperar la lista existente de Pokémon favoritos
                Gson gson = new Gson();
                String pokemonListJson = sharedPreferences.getString("pokemon_list", "");
                List<PokemonDetails> pokemonList;

                if (!pokemonListJson.isEmpty()) {
                    pokemonList = gson.fromJson(pokemonListJson, new TypeToken<List<PokemonDetails>>(){}.getType());
                } else {
                    pokemonList = new ArrayList<>();
                }

                // Añadir el nuevo Pokémon a la lista
                pokemonList.add(pokemonDetails);

                // Guardar la lista actualizada en SharedPreferences
                String updatedPokemonListJson = gson.toJson(pokemonList);
                editor.putString("pokemon_list", updatedPokemonListJson);
                editor.apply();

                // Iniciar la nueva actividad
                Intent intent = new Intent(DetailsPokeActivity.this, FavoritePokeActivity.class);
                startActivity(intent);
            }
        });


    }
}
