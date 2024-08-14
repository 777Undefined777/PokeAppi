package com.example.pokeappi.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokeappi.R;
import com.example.pokeappi.model.PokemonDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class FavoritePokeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoritePokemonAdapter adapter;
    private List<PokemonDetails> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_poke);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuración del RecyclerView
        recyclerView = findViewById(R.id.favorite_pokemon_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Recuperar los datos de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("PokeAppiPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String pokemonListJson = sharedPreferences.getString("pokemon_list", "");

        if (!pokemonListJson.isEmpty()) {
            pokemonList = gson.fromJson(pokemonListJson, new TypeToken<List<PokemonDetails>>(){}.getType());
        } else {
            pokemonList = new ArrayList<>();
        }

        // Configurar el adaptador con la lista de Pokémon
        adapter = new FavoritePokemonAdapter(this, pokemonList);
        recyclerView.setAdapter(adapter);
    }
}
