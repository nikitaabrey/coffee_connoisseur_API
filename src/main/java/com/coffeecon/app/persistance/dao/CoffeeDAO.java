package com.coffeecon.app.persistance.dao;

import com.coffeecon.app.Models.Coffee;

public class CoffeeDAO {

    private static CoffeeDAO _instance;
    public Coffee selectCoffeeById (Integer id){
        return new Coffee();
    }

    /*
    * This will return a single instance of coffee
    * -SINGLETON
    * */
    public static CoffeeDAO getInstance(){
        if (_instance == null){
            _instance = new CoffeeDAO();
        }
        return _instance;
    }
}
