package com.coffeecon.app.Services;

import com.coffeecon.app.Models.Coffee;
import com.coffeecon.app.Models.HttpResponseModels.HttpFailure;
import com.coffeecon.app.Models.HttpResponseModels.HttpSuccess;
import com.coffeecon.app.Repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository repo;
    public Coffee getCoffeeById (Integer id) {
      Coffee coffee =  repo.getCoffeeById(id) ;
     return coffee;
    }
    public List<Coffee> getCoffeesByTags (List<String> tags, String sortKey, String order){
        return repo.getByTags(tags, sortKey, order);
    }

    public List<Coffee> getCoffeesByIngredients(List<String> ingredients, String sortKey, String order){
        return repo.getByIngredients(ingredients, sortKey, order);
    }
    public List<Coffee> getCoffees(){
        return repo.getAll();
    }

}
