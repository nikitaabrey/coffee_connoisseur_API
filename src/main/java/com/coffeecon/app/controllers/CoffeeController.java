package com.coffeecon.app.controllers;

import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.Models.HttpResponseModels.HttpSuccess;
import com.coffeecon.app.Models.Ingredient;
import com.coffeecon.app.Models.Recipe;
import com.coffeecon.app.Services.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class will contain the endpoints for coffees
 */
@RequestMapping ("/coffees")
@RestController
@Validated
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @RequestMapping (value="",method = RequestMethod.GET)
    public ResponseEntity<Object>  getCoffeesbyTagsOrIngredients (@RequestParam (defaultValue="none") @Pattern(regexp = "([a-zA-Z]+'?[a-zA-Z]+,?)+") String  tags,
                                                                  @RequestParam (defaultValue="none") @Pattern(regexp = "([a-zA-Z]+'?[a-zA-Z]+,?)+") String ingredients ,
                                                                  @RequestParam (defaultValue="none")  @Pattern(regexp = "^(difficulty|rating|none)$") String sort_key ,
                                                                  @RequestParam (defaultValue="none") @Pattern(regexp = "^(desc|asc|none)$") String order,
                                                                  @RequestParam (defaultValue = "none") @Pattern(regexp = "^(1|2|3|4|5|none)$") String level) {

        List<Coffee> coffees= null;

        if (tags.equals("none") && ingredients.equals("none") && level.equals("none")){
            coffees = coffeeService.getCoffees();
        }

        if (!tags.equals("none")) {
            coffees = coffeeService.getCoffeesByTags(Arrays.asList(tags.split(",")), sort_key, order);
        }
        else if (!ingredients.equals("none")){
            coffees= coffeeService.getCoffeesByIngredients(Arrays.asList(ingredients.split(",")), sort_key, order);
        }
        else if (!level.equals("none")) {
            coffees = coffeeService.getCoffeeByDifficulty(Integer.parseInt(level));
        }

        return new HttpSuccess.Builder<List<Coffee>>(HttpStatus.OK)
                .withBody(coffees).build();
    }

    @PutMapping(value="")
    public ResponseEntity<?> updateCoffeeRating(@RequestParam int coffeeId, @RequestParam @Pattern(regexp = "^(1|2|3|4|5)$") int rating) {
        coffeeService.updateCoffeeRating(coffeeId, rating);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping(value="")
//    public ResponseEntity<?> newCoffeeRating(@RequestParam int coffeeId, @RequestParam int rating) {
//        coffeeService.newCoffeeRating(coffeeId, rating);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> selectCoffeeById(@PathVariable (value="id")int id) {
        Coffee coffee= coffeeService.getCoffeeById(id);
        return new HttpSuccess.Builder<Coffee>(HttpStatus.OK)
                .withBody(coffee).build();
    }

}




