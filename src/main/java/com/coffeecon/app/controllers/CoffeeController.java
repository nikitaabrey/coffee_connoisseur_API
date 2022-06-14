package com.coffeecon.app.controllers;


import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.Models.Ingredient;
import com.coffeecon.app.Models.Recipe;
import com.coffeecon.app.Services.CoffeeService;
import com.coffeecon.app.Services.CoffeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;



/**
 * This class will contain the endpoints for coffees
 */
@RestController
public class CoffeeController {


    @Autowired
    public CoffeeService _CoffeeService;

    public CoffeeController(CoffeeService coffeeService){
        this._CoffeeService = coffeeService;
    }

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

  ;
        return new ResponseEntity<Coffee>(coffee, HttpStatus.OK);
    }

    @RequestMapping(value="/coffee/{id}", method = RequestMethod.GET)
    public ResponseEntity<Coffee> selectCoffeeById(@PathVariable (value="id")Integer id){
        Coffee coffee= _CoffeeService.getCoffeeById(id);

        System.out.println("Received new query.New Coffee ="+coffee.toString());
        return new ResponseEntity<Coffee>(HttpStatus.OK);
    }

}




