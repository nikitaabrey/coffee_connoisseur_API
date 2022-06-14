package com.coffeecon.app.Models;

import java.util.List;

public class Recipe {

    private int recipeId;
    private String name;
    private String instructions;
    private String description;
    private double prepTime;
    private int difficulty;
    private List<Ingredient> ingredients;

    public Recipe() {

    }

    public Recipe(int recipeID, String name, String instructions, String description, double prepTime, int difficulty,List<Ingredient> ingredients) {
        this.recipeId = recipeID;
        this.name = name;
        this.instructions = instructions;
        this.description = description;
        this.prepTime = prepTime;
        this.difficulty = difficulty;
        this.ingredients = ingredients;
    }


    public Recipe( String name, String instructions, String description, double prepTime, int difficulty) {
        this.name = name;
        this.instructions = instructions;
        this.description = description;
        this.prepTime = prepTime;
        this.difficulty = difficulty;
    }


    public int getRecipeID() {
        return this.recipeId;
    }

    public void setRecipeID(int recipeID) {
        this.recipeId = recipeID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrepTime() {
        return this.prepTime;
    }

    public void setPrepTime(double prepTime) {
        this.prepTime = prepTime;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


}
