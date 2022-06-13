package com.coffeecon.app.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.coffeecon.app.Models.Coffee;
import org.springframework.jdbc.core.RowMapper;

import com.coffeecon.app.Models.Recipe;

public class CoffeeRowMapper implements RowMapper<Coffee> {

	@Override
	public Coffee mapRow(ResultSet rs, int rowNum) throws SQLException {

		Recipe recipe = new Recipe();
		recipe.setRecipeID(rs.getInt("RecipeID"));
		recipe.setName(rs.getString("Name"));
		recipe.setDescription(rs.getString("Description"));
		recipe.setInstructions(rs.getString("Instructions"));
		recipe.setPrepTime(rs.getDouble("PrepTime"));
		recipe.setDifficulty(rs.getInt("DifficultyID"));

		Coffee coffee = new Coffee();
		coffee.setCoffeeID(rs.getInt("CoffeeID"));
		coffee.setRecipe(recipe);
		coffee.setName(rs.getString("Name"));
		coffee.setDescription(rs.getString("Description"));
		coffee.setRating(rs.getInt("Rating"));

		return coffee;
	}
}