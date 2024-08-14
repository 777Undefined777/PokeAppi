package com.example.pokeappi.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.pokeappi.R;

public class DetailsPokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_poke);

        // Obtener los datos del Intent
        String pokemonName = getIntent().getStringExtra("pokemon_name");
        String pokemonImageUrl = getIntent().getStringExtra("pokemon_image_url");

        // Mostrar los datos recibidos en la UI (ejemplo)
        TextView nameTextView = findViewById(R.id.details_pokemon_name);
        ImageView imageView = findViewById(R.id.details_pokemon_image);

        nameTextView.setText(pokemonName);
        Glide.with(this).load(pokemonImageUrl).into(imageView);
    }
}
