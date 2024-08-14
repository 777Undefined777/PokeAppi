package com.example.pokeappi.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokeappi.R;
import com.example.pokeappi.model.PokemonDetails;

import java.util.List;

public class FavoritePokemonAdapter extends RecyclerView.Adapter<FavoritePokemonAdapter.PokemonViewHolder> {

    private Context context;
    private List<PokemonDetails> pokemonList;

    public FavoritePokemonAdapter(Context context, List<PokemonDetails> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite_pokemon, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        PokemonDetails pokemon = pokemonList.get(position);
        holder.nameTextView.setText(pokemon.getName());

        StringBuilder statsBuilder = new StringBuilder();
        for (PokemonDetails.Stat stat : pokemon.getStats()) {
            statsBuilder.append(stat.getStat().getName())
                    .append(": ")
                    .append(stat.getBaseStat())
                    .append("\n");
        }
        holder.statsTextView.setText(statsBuilder.toString());

        Glide.with(context).load(pokemon.getSprites().getFrontDefault()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameTextView;
        TextView statsTextView;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_pokemon_image);
            nameTextView = itemView.findViewById(R.id.item_pokemon_name);
            statsTextView = itemView.findViewById(R.id.item_pokemon_stats);
        }
    }
}
