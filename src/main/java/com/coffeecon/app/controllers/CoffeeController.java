package com.coffeecon.app.controllers;

import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.Models.Ingredient;
import com.coffeecon.app.Models.Recipe;
import com.coffeecon.app.Services.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class will contain the endpoints for coffees
 */
@RequestMapping ("/coffees")
@RestController
public class CoffeeController {

    //GET /api/coffees?tags={tag}&sort-by={value} value=rating, difficulty
    //GET /api/coffees?ingredients={ingredient}&sort-by={value} value=rating, difficulty

    @Autowired
    private CoffeeService service;

    @RequestMapping(value="/example", method = RequestMethod.GET)
    public ResponseEntity<Coffee> test() {

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

        return new ResponseEntity<Coffee>(coffee, HttpStatus.OK);
    }

    @RequestMapping (value="",method = RequestMethod.GET)
    public ResponseEntity<List<Coffee>> getCoffeesbyTagsOrIngredients (@RequestParam (defaultValue="none") String  tags, @RequestParam (defaultValue="none") String ingredients ,
                                                                       @RequestParam (defaultValue="none")String sort_key ,
                                                                       @RequestParam (defaultValue="none") String order) {
        List<Coffee> coffees= null;
        if (!tags.equals("none")) {
            coffees = service.getCoffeesByTags(Arrays.asList(tags.split(",")), sort_key, order);
        }
        else if (!ingredients.equals("none"))
        coffees= service.getCoffeesByIngredients(Arrays.asList(ingredients.split(",")), sort_key, order);

        return new ResponseEntity<List<Coffee>>(coffees, HttpStatus.OK);
    }

}




