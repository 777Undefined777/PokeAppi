package com.example.pokeappi.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokeappi.R;
import com.example.pokeappi.model.Pokemon;

import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder> {

    private final List<Pokemon> pokemonList;
    private final Context context;

    public CarouselAdapter(List<Pokemon> pokemonList, Context context) {
        this.pokemonList = pokemonList;
        this.context = context;
    }

    @NonNull
    @Override
    public CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_carousel, parent, false);
        return new CarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);

        Glide.with(context)
                .load(pokemon.getImageUrl())
                .placeholder(R.drawable.pika)  // Imagen de placeholder mientras carga
                .into(holder.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public static class CarouselViewHolder extends RecyclerView.ViewHolder {
        ImageView pokemonImage;

        public CarouselViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonImage = itemView.findViewById(R.id.pokemon_image);
        }
    }
}
