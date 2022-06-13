package com.coffeecon.app.Services;
import com.coffeecon.app.Models.Coffee;

import com.coffeecon.app.Repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository repo;

    public List<Coffee> getCoffees(){
        return repo.getAll();
    }

    public List<Coffee> getCoffeeByDifficulty(int level) {
        return repo.getByDifficulty(level);
    }

    public void updateCoffeeRating(int coffeeId, int rating) {
        repo.updateRating(coffeeId, rating);
    }
}
