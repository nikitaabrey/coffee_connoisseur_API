package com.coffeecon.app.Repositories;

import com.coffeecon.app.Models.Coffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoffeeRepository implements ICoffeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String GET_COFFEES_QUERY = "SELECT * FROM CoffeeRecipeView";

    // SELECT * FROM Recipe WHERE difficultyId = "";

    @Override
    public List<Coffee> getAll(String sort_key, String order) {
      return null;
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
        return null;
    }

    @Override
    public void updateRating(int coffeeId, int rating) {
        
    }
}
