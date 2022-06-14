package com.coffeecon.app.Models;

import java.util.List;

public class Coffee {

    // coffee model


    private Recipe recipe;

    private int CoffeeID;
    private String name;
    private String description;
    private int rating;
    private List<Tag> tags;

    public Coffee() {

    }

    public Coffee(Recipe recipe, int coffeeID, String name, String description, int rating, List<Tag> tags) {
        this.recipe = recipe;
        this.CoffeeID = coffeeID;
        this.name = name;
        this.description = description;
        this.rating = rating;
        // this.tags = tags;
    }

    public Coffee(Recipe recipe, String name, String description, int rating) {
        this.recipe = recipe;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getCoffeeID() {
        return CoffeeID;
    }

    public void setCoffeeID(int coffeeID) {
        CoffeeID = coffeeID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
