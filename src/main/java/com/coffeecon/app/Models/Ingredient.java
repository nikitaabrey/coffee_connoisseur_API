package com.coffeecon.app.Models;

public class Ingredient {

    private int IngredientID;
    private String name;
    private String Quantity;
    private String Unit;

    public Ingredient() {

    }

    public Ingredient(int IngredientID,String name, String quantity, String unit) {
        this.name = name;
        this.Quantity = quantity;
        this.Unit = unit;
        this.IngredientID = IngredientID;
    }

    public Ingredient(String name, String quantity, String unit) {
        this.name = name;
        this.Quantity = quantity;
        this.Unit = unit;
    }

    public int getIngredientID() {
        return IngredientID;
    }

    public void setIngredientID(int ingredientID) {
        IngredientID = ingredientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }
}
