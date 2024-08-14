package com.example.pokeappi.model;

public class Pokemon {
    private String name;
    private String url;

    private String imageUrl;  // Agrega esta línea


    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }


    public String getImageUrl() {
        return imageUrl;  // Agrega este método

    }

    public void setImageUrl(String imageUrl) {  // Agrega este método
        this.imageUrl = imageUrl;
    }

}
