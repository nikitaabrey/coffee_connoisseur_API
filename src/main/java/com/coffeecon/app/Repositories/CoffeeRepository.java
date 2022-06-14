package com.coffeecon.app.Repositories;
import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.Mappers.CoffeeRowMapper;

import com.coffeecon.app.Models.Ingredient;
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
    private final String GET_COFFEE_DIFFICULTIES = "SELECT Recipe.RecipeID, Recipe.Name, Recipe.Description, Recipe.Instructions, Recipe.PrepTime, Recipe.DifficultyId, Coffee.CoffeeID, Coffee.Name, Coffee.Description, Coffee.RecipeID, Coffee.Rating, Difficulty.Level FROM Recipe INNER JOIN Coffee ON Recipe.RecipeID = Coffee.RecipeID INNER JOIN Difficulty ON Recipe.DifficultyID = Difficulty.DifficultyID WHERE Difficulty.Level = ?";
    private final String UPDATE_COFFEE_RATING = "UPDATE CoffeeRating SET LastRating = ? WHERE CoffeeID = ?";
    private final String UPDATE_AVG_RATING = "UPDATE Coffee SET Rating = ? WHERE CoffeeID = ?";
    private final String AVERAGE_RATING = "SELECT AVG(LastRating) AS rating FROM CoffeeRating WHERE CoffeeID = ?";
    private final String NEW_RATING = "INSERT INTO CoffeeRating (UserID, CoffeeID, LastRating) VALUES ('?', ?, ?) ON DUPLICATE KEY UPDATE LastRating = ?";

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
    public void updateRating(int coffeeId, int rating) {
        jdbcTemplate.update(UPDATE_COFFEE_RATING, rating, coffeeId);
        int avgRating = jdbcTemplate.update(AVERAGE_RATING);
        jdbcTemplate.update(UPDATE_AVG_RATING, avgRating, coffeeId);
    }

    @Override
    public void newRating(int coffeeId, int rating) {
        jdbcTemplate.update(NEW_RATING, userId, coffeeId, rating, rating);
    }
}
