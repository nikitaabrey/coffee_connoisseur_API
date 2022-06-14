package com.coffeecon.app.controllers;

import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.Models.HttpResponseModels.HttpSuccess;
import com.coffeecon.app.Services.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

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
                                                                       @RequestParam (defaultValue="none")String sort_key , @RequestParam (defaultValue="none") String order) {
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

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> selectCoffeeById(@PathVariable (value="id")int id) {
        Coffee coffee= coffeeService.getCoffeeById(id);
        return new HttpSuccess.Builder<Coffee>(HttpStatus.OK)
                .withBody(coffee).build();
    }

}




