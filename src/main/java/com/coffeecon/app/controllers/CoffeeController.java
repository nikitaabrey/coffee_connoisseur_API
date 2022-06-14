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


    @Autowired
    private CoffeeService coffeeService;






    @RequestMapping (value="",method = RequestMethod.GET)
    public ResponseEntity<Object>  getCoffeesbyTagsOrIngredients (@RequestParam (defaultValue="none") String  tags, @RequestParam (defaultValue="none") String ingredients ,
                                                                       @RequestParam (defaultValue="none")String sort_key ,
                                                                       @RequestParam (defaultValue="none") String order) {
        List<Coffee> coffees= null;
        if (!tags.equals("none")) {
            coffees = coffeeService.getCoffeesByTags(Arrays.asList(tags.split(",")), sort_key, order);
        }
        else if (!ingredients.equals("none")){
            coffees= coffeeService.getCoffeesByIngredients(Arrays.asList(ingredients.split(",")), sort_key, order);
        }

        if (tags.equals("none") && ingredients.equals("none")){
            coffees = coffeeService.getCoffees();
        }

        return new HttpSuccess.Builder<List<Coffee>>(HttpStatus.OK)
                .withBody(coffees).build();
    }

    @RequestMapping(value="/coffee", method = RequestMethod.GET)
    public ResponseEntity<?> getCoffees() {

        List<Coffee> coffees = coffeeService.getCoffees();
        return new ResponseEntity<>(coffees,HttpStatus.OK);
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity<?> getCoffeeDifficulty(@RequestParam int level) {
        List<Coffee> coffees = coffeeService.getCoffeeByDifficulty(level);
        return new ResponseEntity<>(coffees, HttpStatus.OK);
    }

    @PutMapping(value="")
    public ResponseEntity<?> updateCoffeeRating(@RequestParam int coffeeId, @RequestParam int rating) {
        coffeeService.updateCoffeeRating(coffeeId, rating);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value="")
    public ResponseEntity<?> newCoffeeRating(@RequestParam int coffeeId, @RequestParam int rating) {
        coffeeService.newCoffeeRating(coffeeId, rating);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}




