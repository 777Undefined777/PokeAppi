package com.example.pokeappi.view;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pokeappi.R;
import com.example.pokeappi.model.Pokemon;
import com.example.pokeappi.presenter.PokemonPresenter;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PokemonView {

    private PokemonPresenter presenter;
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        // Configura el GridLayoutManager con 2 columnas
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        presenter = new PokemonPresenter(this);
        presenter.getPokemonData();
    }

    @Override
    public void showPokemonList(List<Pokemon> pokemonList) {
        adapter = new PokemonAdapter(pokemonList, presenter);  // Pass the presenter here
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
