package com.coffeecon.app.Repositories;

import com.coffeecon.app.Models.Coffee;

import com.coffeecon.app.Models.Ingredient;
import com.coffeecon.app.Models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        Coffee coffeeResult= jdbcTemplate.queryForObject(
                String.format("SELECT * FROM Coffee WHERE CoffeeID = %d", id ), new RowMapper<Coffee>(){
                    @Override
                    public Coffee mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        Ingredient ingredient1 = new Ingredient(1,"milk","400","ml");
                        Ingredient ingredient2 = new Ingredient(2,"sugar","2","tsp");

                        Recipe recipe = new Recipe(1,"Expresso","add milk and expresso with sugar",2.5,3,new ArrayList<Ingredient>() {
                            {
                                add(ingredient1);
                                add(ingredient2);
                            }
                        });

                        List<String> tags = new ArrayList<String>() {{
                            add("dark roast");
                            add("floral");
                        }};

                        Coffee coffee = new Coffee(recipe,1,"expresso","this is an expresso",3, tags);
                        return coffee;
                    }
                });
        System.out.println(coffeeResult);
        return coffeeResult;
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
