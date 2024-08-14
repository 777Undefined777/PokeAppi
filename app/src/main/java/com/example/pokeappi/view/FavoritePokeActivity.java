package com.example.pokeappi.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.pokeappi.R;
import com.example.pokeappi.model.PokemonDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class FavoritePokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_poke);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Recuperar los datos de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("PokeAppiPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String pokemonListJson = sharedPreferences.getString("pokemon_list", "");

        List<PokemonDetails> pokemonList;
        if (!pokemonListJson.isEmpty()) {
            pokemonList = gson.fromJson(pokemonListJson, new TypeToken<List<PokemonDetails>>(){}.getType());
        } else {
            pokemonList = new ArrayList<>();
        }

        // Mostrar los datos en la UI (esto es solo un ejemplo; puedes usar un RecyclerView para una mejor presentación)
        TextView nameTextView = findViewById(R.id.favorite_pokemon_name);
        ImageView imageView = findViewById(R.id.favorite_pokemon_image);
        TextView statsTextView = findViewById(R.id.favorite_pokemon_stats);

        StringBuilder allPokemonNames = new StringBuilder();
        StringBuilder allPokemonStats = new StringBuilder();

        for (PokemonDetails pokemon : pokemonList) {
            allPokemonNames.append(pokemon.getName()).append("\n");
            for (PokemonDetails.Stat stat : pokemon.getStats()) {
                allPokemonStats.append(stat.getStat().getName())
                        .append(": ")
                        .append(stat.getBaseStat())
                        .append("\n");
            }
        }

        nameTextView.setText(allPokemonNames.toString());
        statsTextView.setText(allPokemonStats.toString());
    }
    }

