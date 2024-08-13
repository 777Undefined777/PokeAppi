package com.example.pokeappi.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

=======
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
>>>>>>> 604e6cf (TRaida de imagenes con glide)
import com.example.pokeappi.R;
import com.example.pokeappi.model.Pokemon;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<Pokemon> pokemonList;

    public PokemonAdapter(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
<<<<<<< HEAD
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
=======
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);
>>>>>>> 604e6cf (TRaida de imagenes con glide)
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
<<<<<<< HEAD
        holder.nameTextView.setText(pokemon.getName());
=======
        holder.pokemonName.setText(pokemon.getName());
        Glide.with(holder.itemView.getContext())
                .load(pokemon.getImageUrl())
                .into(holder.pokemonImage);
>>>>>>> 604e6cf (TRaida de imagenes con glide)
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

<<<<<<< HEAD
    static class PokemonViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.pokemon_name);
=======
    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        TextView pokemonName;
        ImageView pokemonImage;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonName = itemView.findViewById(R.id.pokemon_name);
            pokemonImage = itemView.findViewById(R.id.pokemon_image);
>>>>>>> 604e6cf (TRaida de imagenes con glide)
        }
    }
}
