package com.coffeecon.app.controllers;

import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.Services.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * This class will contain the endpoints for coffees
 */
@RestController
public class CoffeeController {


    @Autowired
    private CoffeeService coffeeService;

    @RequestMapping(value="/coffee", method = RequestMethod.GET)
    public ResponseEntity<?> getCoffees() {

        List<Coffee> coffees = coffeeService.getCoffees();
        return new ResponseEntity<>(coffees,HttpStatus.OK);
    }

}




