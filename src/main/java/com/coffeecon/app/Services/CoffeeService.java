package com.coffeecon.app.Services;

import com.coffeecon.app.Models.Coffee;

import com.coffeecon.app.Repositories.CoffeeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository repo;

    public List<Coffee> getCoffees(){
        return repo.getAll();
    }

}