package com.poke.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    private int id;
    private String name;
    private int height;
    private int weight;
    private Sprites sprites;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sprites{
        private String front_default;

        public String getFront_default() {
            return front_default;
        }
        public void setFront_default(String front_default) {
             this.front_default = front_default;
        }
    }
}
