package com.coffeecon.app.Services;


import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.Repositories.CoffeeRepository;
import com.coffeecon.app.persistance.dao.CoffeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CoffeeServiceImpl implements CoffeeService  {

    @Autowired
    private CoffeeRepository repo;
    private CoffeeDAO _CoffeeDAO;
    public CoffeeServiceImpl(){
        _CoffeeDAO = CoffeeDAO.getInstance();
    }


    @Override
    public Coffee getCoffeeById(Integer id) {
       return _CoffeeDAO.selectCoffeeById(id);
    }
}

