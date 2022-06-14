package com.coffeecon.app.Repositories;

import com.coffeecon.app.Mappers.CoffeeRowMapper;
import com.coffeecon.app.Models.Coffee;

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
        String queryByTags = "SELECT * FROM Coffee c\n" +
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

        return querySortedResults(queryByTags, sort_key, order);
    }

    @Override
    public List<Coffee> getByIngredients(List<String> ingredients, String sort_key, String order) {
        String querybyIngredients = "SELECT *  FROM Coffee c\n" +
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
       return querySortedResults(querybyIngredients, sort_key, order);
    }

    public List<Coffee> querySortedResults (String query, String sort_key, String order)
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
        return null;
    }

    @Override
    public void updateRating(int coffeeId, int rating) {
    }
}
