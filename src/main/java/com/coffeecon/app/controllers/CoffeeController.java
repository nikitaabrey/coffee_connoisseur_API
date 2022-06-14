package com.coffeecon.app.controllers;

import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.Models.HttpResponseModels.HttpSuccess;
import com.coffeecon.app.Services.CoffeeService;
import com.coffeecon.app.Utilities.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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

    @RequestMapping(value="/coffees", method = RequestMethod.GET)
    public ResponseEntity<Object> getCoffees() {

        List<Coffee> coffees = coffeeService.getCoffees();
        return new HttpSuccess.Builder<List<Coffee>>(HttpStatus.OK)
                .withBody(coffees).build();

    }

}




