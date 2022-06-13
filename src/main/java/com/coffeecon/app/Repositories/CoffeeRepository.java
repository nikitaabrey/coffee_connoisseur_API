package com.coffeecon.app.Repositories;
import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.Mappers.CoffeeRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoffeeRepository implements ICoffeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String GET_COFFEES_QUERY = "SELECT * FROM CoffeeRecipeView";
    private final String GET_COFFEE_DIFFICULTIES = "SELECT Recipe.RecipeID, Recipe.Name, Recipe.Description, Recipe.Instructions, Recipe.PrepTime, Recipe.DifficultyId, Coffee.CoffeeID, Coffee.Name, Coffee.Description, Coffee.RecipeID, Coffee.Rating, Difficulty.Level FROM Recipe INNER JOIN Coffee ON Recipe.RecipeID = Coffee.RecipeID INNER JOIN Difficulty ON Recipe.DifficultyID = Difficulty.DifficultyID WHERE Difficulty.Level = ?";
    private final String UPDATE_COFFEE_RATING = "UPDATE Coffee SET Rating = ? WHERE CoffeeID = ?";

    @Override
    public List<Coffee> getAll() {
        List<Coffee> coffees = jdbcTemplate.query(GET_COFFEES_QUERY, new CoffeeRowMapper());
        return coffees;
    }

    @Override
    public Coffee getCoffeeById(int id) {
        return null;
    }

    @Override
    public List<Coffee> getByTags(List<String> tags, String sort_key, String order) {
        return null;
    }

    @Override
    public List<Coffee> getByIngredients(List<String> ingredients, String sort_key, String order) {
        return null;
    }

    @Override
    public List<Coffee> getByDifficulty(int level) {
        List<Coffee> coffees = jdbcTemplate.query(GET_COFFEE_DIFFICULTIES, new CoffeeRowMapper(), level);
        return coffees;
    }

    @Override
    public void updateRating(int coffeeId, int rating) {
        jdbcTemplate.update(UPDATE_COFFEE_RATING, rating, coffeeId);
    }
}
