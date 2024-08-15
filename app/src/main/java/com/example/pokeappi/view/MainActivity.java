package com.example.pokeappi.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokeappi.R;
import com.example.pokeappi.model.Pokemon;
import com.example.pokeappi.presenter.PokemonPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PokemonView {

    private PokemonPresenter presenter;
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    private RecyclerView carouselRecyclerView;
    private CarouselAdapter carouselAdapter;
    private Handler handler;
    private Runnable runnable;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        carouselRecyclerView = findViewById(R.id.carousel_recycler_view);

        // Configura el GridLayoutManager con 2 columnas
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Configura el LinearLayoutManager para el carrusel
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        carouselRecyclerView.setLayoutManager(layoutManager);

        presenter = new PokemonPresenter(this);
        presenter.getPokemonData();

        // Configura el auto-scroll del carrusel
        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPosition == carouselAdapter.getItemCount() - 1) {
                    currentPosition = 0;
                } else {
                    currentPosition++;
                }
                carouselRecyclerView.smoothScrollToPosition(currentPosition);
                handler.postDelayed(this, 3000);  // 3000 milisegundos
            }
        };
    }

    @Override
    public void showPokemonList(List<Pokemon> pokemonList) {
        // Limitar la lista de Pokémones a 10 para el carrusel
        List<Pokemon> carouselList = pokemonList.subList(0, Math.min(10, pokemonList.size()));

        // Configurar el adaptador para el carrusel
        carouselAdapter = new CarouselAdapter(carouselList, this);
        carouselRecyclerView.setAdapter(carouselAdapter);

        // Configurar el adaptador para el GridView
        adapter = new PokemonAdapter(pokemonList, presenter);
        recyclerView.setAdapter(adapter);

        // Iniciar el auto-scroll del carrusel
        handler.postDelayed(runnable, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Detener el auto-scroll cuando la actividad sea destruida
        handler.removeCallbacks(runnable);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
