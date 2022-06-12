package com.coffeecon.app.persistance.dto;

import com.coffeecon.app.Models.Recipe;

import java.util.List;

public class CoffeeDTO {
    private Recipe recipe;
    private int CoffeeID;
    private String Name;
    private String description;
    private int Rating;
    private List<String> tags;

    public CoffeeDTO(Recipe recipe, int coffeeID, String name, String description, int rating, List<String> tags) {
        this.recipe = recipe;
        CoffeeID = coffeeID;
        Name = name;
        this.description = description;
        Rating = rating;
        this.tags = tags;
    }
}
