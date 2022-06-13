package com.coffeecon.app.Models;


import java.util.List;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;


@Data
public class Coffee {

    private Recipe recipe;
    @Id
    private int CoffeeID;
    private String name;
    private String description;
    private int rating;
    @MappedCollection(idColumn = "CoffeeID")
    // private List<TagRef> tags;
    private List<Tag> tags;

    public Coffee() {

    }

    public Coffee(Recipe recipe, int coffeeID, String name, String description, int rating, List<Tag> tags) {
        this.recipe = recipe;
        this.CoffeeID = coffeeID;
        this.name = name;
        this.description = description;
        this.rating = rating;
        // this.tags = tags;
    }

    public Coffee(Recipe recipe, String name, String description, int rating) {
        this.recipe = recipe;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getCoffeeID() {
        return CoffeeID;
    }

    public void setCoffeeID(int coffeeID) {
        CoffeeID = coffeeID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    // public void addTag(Tag tag) {
    //     // new TagRef()
	// 	this.tags.add(new TagRef(tag.getTagId()));
	// }


}
