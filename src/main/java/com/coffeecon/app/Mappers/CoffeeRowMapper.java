package com.coffeecon.app.Mappers;

import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.Models.Ingredient;
import com.coffeecon.app.Models.Recipe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoffeeRowMapper implements RowMapper<Coffee> {
    @Override
    public Coffee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Coffee coffee = new Coffee();
        coffee.setCoffeeID(rs.getInt("c.CoffeeID"));
        coffee.setName(rs.getString("Name"));
        coffee.setDescription(rs.getString("Description"));
        coffee.setRating(rs.getInt("Rating"));

        Recipe recipe = new Recipe ();
        recipe.setRecipeID(rs.getInt("RecipeID"));
        recipe.setName(rs.getString("r.Name"));
        recipe.setInstructions(rs.getString("r.Instructions"));
        recipe.setPrepTime(rs.getInt("r.PrepTime"));
        recipe.setDifficulty(rs.getInt("d.Level"));

        //Ingredient ingredient= new Ingredient(rs.getInt("IngredientID"), rs.getString("i.Name"),
        //rs.getString("Quantity"),rs.getString("UnitID"));
        // List<Ingredient>ingredientList= new  ArrayList<Ingredient>();
        //ingredientList.add(ingredient);
       // recipe.setIngredients(ingredientList);
        coffee.setRecipe(recipe);
        return coffee;
    }
}
