package com.coffeecon.app.Models;

import java.util.List;

public class Recipe {
    private int RecipeID;
    private String Name;
    private String instructions;
    private String Description;
    private double PrepTime;
    private int Difficulty;
    private List<Ingredient> ingredients;

    public Recipe() {

    }

    public Recipe(int recipeID, String name, String instructions, String description, double prepTime, int difficulty,List<Ingredient> ingredients) {
        this.RecipeID = recipeID;
        this.Name = name;
        this.instructions = instructions;
        this.Description = description;
        this.PrepTime = prepTime;
        this.Difficulty = difficulty;
        this.ingredients = ingredients;
    }

    public Recipe(String name, String instructions, String description, double prepTime, int difficulty) {
        this.Name = name;
        this.instructions = instructions;
        this.Description = description;
        this.PrepTime = prepTime;
        this.Difficulty = difficulty;
    }

    public int getRecipeID() {
        return RecipeID;
    }

    public void setRecipeID(int recipeID) {
        RecipeID = recipeID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public double getPrepTime() {
        return PrepTime;
    }

    public void setPrepTime(double prepTime) {
        PrepTime = prepTime;
    }

    public int getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(int difficulty) {
        Difficulty = difficulty;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
