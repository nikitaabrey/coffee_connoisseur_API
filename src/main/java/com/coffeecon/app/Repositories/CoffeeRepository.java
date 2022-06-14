package com.coffeecon.app.Repositories;

import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.Models.Ingredient;
import com.coffeecon.app.Models.Tag;

import com.coffeecon.app.Mappers.CoffeeRowMapper;
import com.coffeecon.app.Mappers.IngredientRowMapper;
import com.coffeecon.app.Mappers.TagRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoffeeRepository implements ICoffeeRepository { 

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String GET_COFFEES_QUERY = "SELECT * FROM CoffeeRecipeView";
    private final String GET_COFFEE_INGREDIENTS_QUERY = "CALL GetCoffeeIngredients(?)";
    private final String GET_COFFEE_TAGS_QUERY = "CALL GetCoffeeTags(?)";


    @Override
    public List<Coffee> getAll() {


        // L
        List<Coffee> coffees = jdbcTemplate.query(GET_COFFEES_QUERY, new CoffeeRowMapper());
        for( int i = 0; i < coffees.size(); i++){
            int coffeeId = coffees.get(i).getCoffeeID();

            List<Tag> tags = jdbcTemplate.query(GET_COFFEE_TAGS_QUERY, new TagRowMapper(), new Object[] {coffeeId});
            List<Ingredient> ingredients = jdbcTemplate.query(GET_COFFEE_INGREDIENTS_QUERY,new IngredientRowMapper(), new Object[] {coffeeId});

            coffees.get(i).setTags(tags);
            coffees.get(i).getRecipe().setIngredients(ingredients);
            
        }
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
        return null;
    }

    @Override
    public void updateRating(int coffeeId, int rating) {

    }

    @Override
    public List<Coffee> getAll(String sort_key, String order) {
        return null;
    }
}
