package com.coffeecon.app.Models;

import java.util.List;

public class Coffee {

    // coffee model


    private Recipe recipe;

    private int CoffeeID;
    private String Name;
    private String description;
    private int Rating;



    private List<String> tags;


    public Coffee() {

    }

    public Coffee(Recipe recipe, int coffeeID, String name, String description, int rating,List<String> tags) {
        this.recipe = recipe;
        this.CoffeeID = coffeeID;
        this.Name = name;
        this.description = description;
        this.Rating = rating;
        this.tags = tags;
    }



    public Coffee(Recipe recipe, String name, String description, int rating) {
        this.recipe = recipe;
        this.Name = name;
        this.description = description;
        this.Rating = rating;
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
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


}
