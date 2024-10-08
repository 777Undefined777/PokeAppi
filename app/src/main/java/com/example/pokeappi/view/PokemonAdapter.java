package com.example.pokeappi.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokeappi.R;
import com.example.pokeappi.model.Pokemon;
import com.example.pokeappi.model.PokemonDetails;
import com.example.pokeappi.presenter.PokemonPresenter;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<Pokemon> pokemonList;
    private PokemonPresenter presenter;

    public PokemonAdapter(List<Pokemon> pokemonList, PokemonPresenter presenter) {
        this.pokemonList = pokemonList;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        holder.pokemonName.setText(pokemon.getName());
        Glide.with(holder.itemView.getContext())
                .load(pokemon.getImageUrl())
                .into(holder.pokemonImage);

        holder.itemView.setOnClickListener(v -> {
            // Obtener detalles del Pokémon
            presenter.getPokemonDetails(pokemon.getName(), new PokemonPresenter.OnPokemonDetailsReceivedListener() {
                @Override
                public void onPokemonDetailsReceived(PokemonDetails pokemonDetails) {
                    Intent intent = new Intent(holder.itemView.getContext(), DetailsPokeActivity.class);
                    intent.putExtra("pokemon_name", pokemon.getName());
                    intent.putExtra("pokemon_image_url", pokemon.getImageUrl());
                    intent.putExtra("pokemon_details", pokemonDetails);  // Pasa los detalles aquí
                    holder.itemView.getContext().startActivity(intent);
                }

                @Override
                public void onError(String message) {
                    // Manejar el error si ocurre
                }
            });
        });
    }


    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        TextView pokemonName;
        ImageView pokemonImage;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonName = itemView.findViewById(R.id.pokemon_name);
            pokemonImage = itemView.findViewById(R.id.pokemon_image);
        }
    }
}
