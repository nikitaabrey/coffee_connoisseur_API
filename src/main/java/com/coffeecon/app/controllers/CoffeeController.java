package com.coffeecon.app.controllers;

import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.Models.HttpResponseModels.HttpSuccess;
import com.coffeecon.app.Services.CoffeeService;
import com.coffeecon.app.Utilities.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
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

    @GetMapping()
    public ResponseEntity<Object>  getCoffeesbyTagsOrIngredients (@RequestParam (defaultValue="none") @Pattern(regexp = "([a-zA-Z]+'?[a-zA-Z]+,?)+") String tags,
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

    @PutMapping()
    public ResponseEntity<?> updateCoffeeRating(@RequestParam @Pattern(regexp = "^[0-9]+$") String coffeeId, @RequestParam @Pattern(regexp = "^(1|2|3|4|5)$") String rating) {

        coffeeService.updateCoffeeRating(Integer.parseInt(coffeeId), Integer.parseInt(rating));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> newCoffeeRating(@RequestParam @Pattern(regexp = "^[0-9]+$") String coffeeId, @RequestParam @Pattern(regexp = "^(1|2|3|4|5)$") String rating) {

        JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String username = JwtUtils.getUser(token);
        coffeeService.newCoffeeRating(username,Integer.parseInt(coffeeId), Integer.parseInt(rating));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> selectCoffeeById(@PathVariable (value="id") Integer id) {
        
        Coffee coffee= coffeeService.getCoffeeById(id);
        return new HttpSuccess.Builder<Coffee>(HttpStatus.OK)
                .withBody(coffee).build();
    }

}




