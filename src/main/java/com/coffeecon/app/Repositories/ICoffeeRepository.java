package com.coffeecon.app.Repositories;

import com.coffeecon.app.Models.Coffee;

import java.util.List;

public interface ICoffeeRepository {

    /**
     * Get all coffees
     * @param sort_key the key to be used when sorting ie rating/difficulty
     * @param order the sort order ie ascending or descending
     * @return list off all coffees
     */
    public List<Coffee> getAll(String sort_key, String order);

    /**
     * get a coffee by id
     * @param id the id of the coffee to get
     * @return the coffee
     */
    public Coffee getCoffeeById(int id);


    /**
     * get coffees with given tags
     * @param tags list of search tags
     * @param sort_key  the key to be used when sorting ie rating/difficulty
     * @param order order the sort order ie ascending or descending
     * @return list of coffees matching search criteria
     */
    public List<Coffee> getByTags(List<String> tags, String sort_key, String order);

    /**
     * get coffees with given ingredients
     * @param ingredients list of search tags
     * @param sort_key  the key to be used when sorting ie rating/difficulty
     * @param order order the sort order ie ascending or descending
     * @return list of coffees matching search criteria
     */
    public List<Coffee> getByIngredients(List<String> ingredients, String sort_key, String order);


    /**
     * get all coffes of a certain difficulty
     * @param level
     * @return
     */
    public List<Coffee> getByDifficulty(int level);


    /**
     * update a coffee rating
     * @param coffeeId the coffee to update
     * @param rating the rating values
     */
    public void updateRating(int coffeeId, int rating);


}
