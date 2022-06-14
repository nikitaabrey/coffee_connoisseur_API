package com.coffeecon.app.Services;


import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.Repositories.CoffeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CoffeeServiceImpl implements CoffeeService  {

    @Autowired
    private CoffeeRepository repo;




    @Override
    public Coffee getCoffeeById(Integer id) {
       return repo.getCoffeeById(id);
    }
}

