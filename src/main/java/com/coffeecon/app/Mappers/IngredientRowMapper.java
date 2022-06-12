package com.coffeecon.app.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coffeecon.app.Models.Ingredient;

public class IngredientRowMapper implements RowMapper<Ingredient> {

    @Override
	public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {

       return null;
	}
}