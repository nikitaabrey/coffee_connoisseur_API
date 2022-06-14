package com.coffeecon.app.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.coffeecon.app.Models.Ingredient;


public class IngredientRowMapper implements RowMapper<Ingredient> {

    @Override
	public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientID(rs.getInt("IngredientID"));
        ingredient.setName(rs.getString("Name"));
        ingredient.setQuantity(rs.getString("Quantity"));
        ingredient.setUnit(rs.getString("Unit"));

        return ingredient;
       
	}
}