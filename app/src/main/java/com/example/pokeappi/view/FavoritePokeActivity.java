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
        String pokemonName = sharedPreferences.getString("pokemon_name", "Unknown");
        String pokemonImageUrl = sharedPreferences.getString("pokemon_image_url", "");
        String pokemonDetailsJson = sharedPreferences.getString("pokemon_details", "");

        Gson gson = new Gson();
        PokemonDetails pokemonDetails = gson.fromJson(pokemonDetailsJson, PokemonDetails.class);

        // Mostrar los datos en la UI
        TextView nameTextView = findViewById(R.id.favorite_pokemon_name);
        ImageView imageView = findViewById(R.id.favorite_pokemon_image);
        TextView statsTextView = findViewById(R.id.favorite_pokemon_stats);

        nameTextView.setText(pokemonName);
        Glide.with(this).load(pokemonImageUrl).into(imageView);

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
    }
}
