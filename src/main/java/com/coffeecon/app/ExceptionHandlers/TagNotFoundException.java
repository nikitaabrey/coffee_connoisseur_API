package com.coffeecon.app.ExceptionHandlers;

public class TagNotFoundException extends RuntimeException {

    public TagNotFoundException(int id) {
        super("Could not find tag " + id);
    }
    
}
