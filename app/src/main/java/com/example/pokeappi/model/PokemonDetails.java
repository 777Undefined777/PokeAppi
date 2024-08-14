package com.example.pokeappi.model;

import java.io.Serializable;
import java.util.List;

public class PokemonDetails implements Serializable {
    private Sprites sprites;
    private List<Stat> stats;

    public Sprites getSprites() {
        return sprites;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public static class Sprites implements Serializable {
        private String front_default;

        public String getFrontDefault() {
            return front_default;
        }
    }

    public static class Stat implements Serializable {
        private StatDetail stat;
        private int base_stat;

        public StatDetail getStat() {
            return stat;
        }

        public int getBaseStat() {
            return base_stat;
        }
    }

    public static class StatDetail implements Serializable {
        private String name;

        public String getName() {
            return name;
        }
    }
}
