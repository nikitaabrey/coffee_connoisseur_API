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
import java.util.Locale;

@Repository
public class CoffeeRepository implements ICoffeeRepository {


    // IMPLEMENT database query logic here

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
        return getTagsAndIngredients(coffees);
    }

    @Override
    public Coffee getCoffeeById(int id) {
        return null;
    }

    private List<Coffee> getTagsAndIngredients (List<Coffee> coffees) {
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
    public List<Coffee> getByTags(List<String> tags, String sort_key, String order) {
        String queryByTags = "SELECT c.CoffeeID, c.Name, c.Description, c.Rating, r.RecipeID, r.Name AS RecipeName," +
                "r.Description AS RecipeDescription, r.Instructions, r.PrepTime, d.`Level` AS Difficulty FROM Coffee c\n" +
                "INNER JOIN CoffeeTag ct ON  c.CoffeeID= ct.CoffeeID\n" +
                "INNER JOIN Tag t ON ct.TagID = t.TagID \n" +
                "INNER JOIN Recipe r USING  (RecipeID)\n" +
                "INNER JOIN Difficulty d USING (DifficultyID)\n" +
                "WHERE  UPPER(t.Name) IN (";

        for (int i=0; i<tags.size(); i++) {
            if (i==tags.size()-1)
                 queryByTags +=  "'"+tags.get(i).toUpperCase(Locale.ROOT) +"')\n ";
            else
            queryByTags +=  "'"+tags.get(i).toUpperCase(Locale.ROOT) +"', " ;
        }
        List<Coffee> coffees= querySortedResults(queryByTags, sort_key, order);
        return getTagsAndIngredients(coffees);
    }

    @Override
    public List<Coffee> getByIngredients(List<String> ingredients, String sort_key, String order) {
        String querybyIngredients = "SELECT c.CoffeeID, c.Name, c.Description, c.Rating, r.RecipeID, r.Name AS RecipeName, " +
                "r.Description AS RecipeDescription, r.Instructions, r.PrepTime, d.`Level` AS Difficulty FROM Coffee c\n" +
                "INNER JOIN Recipe r USING (RecipeID)\n" +
                "INNER JOIN RecipeIngredient ri USING (RecipeID)\n" +
                "INNER JOIN Ingredient i USING (IngredientID)\n" +
                "INNER JOIN Difficulty d USING (DifficultyID)\n" +
                "WHERE UPPER(i.Name) IN (";

        for (int i=0; i<ingredients.size(); i++) {
            if (i==ingredients.size()-1)
                querybyIngredients +=  "'"+ingredients.get(i).toUpperCase(Locale.ROOT) +"') ";
            else
            querybyIngredients +=  "'"+ingredients.get(i).toUpperCase(Locale.ROOT) +"', " ;

        }

        List<Coffee> coffees= querySortedResults(querybyIngredients, sort_key, order);
        return getTagsAndIngredients(coffees);
    }

    private List<Coffee> querySortedResults (String query, String sort_key, String order)
    {
        switch (sort_key) {
            case "rating":
                if (order.equals("desc"))
                    return jdbcTemplate.query(query  + "\n ORDER BY rating " + "DESC\n;", new CoffeeRowMapper());
                else
                    return jdbcTemplate.query(query +  "\n ORDER BY rating " + "ASC\n;", new CoffeeRowMapper());

            case "difficulty":
                if (order.equals("desc"))
                    return jdbcTemplate.query(query +  "\n ORDER BY d.Level " + "DESC\n;", new CoffeeRowMapper());
                else
                    return jdbcTemplate.query(query +  "\n ORDER BY d.Level " + "ASC\n;", new CoffeeRowMapper());
            case "none":
                return jdbcTemplate.query(query, new CoffeeRowMapper());
        }
        return jdbcTemplate.query(query, new CoffeeRowMapper());
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
