package com.coffeecon.app.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.controllers.CoffeeController;

@Component
public class CoffeeModelAssembler implements RepresentationModelAssembler<Coffee, EntityModel<Coffee>>{

    @Override
    public EntityModel<Coffee> toModel(Coffee coffee) {

        return EntityModel.of(coffee, //
            linkTo(methodOn(CoffeeController.class).selectCoffeeById(coffee.getCoffeeID())).withSelfRel(),
            linkTo(methodOn(CoffeeController.class).getCoffeesbyTagsOrIngredients("none","none","none","none","none")).withRel("coffees"));
    }
    
}
