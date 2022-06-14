package com.coffeecon.app.Services;

import com.coffeecon.app.Models.Coffee;
import org.springframework.stereotype.Service;

@Service
public interface CoffeeService {
    public Coffee getCoffeeById (Integer id);
}
