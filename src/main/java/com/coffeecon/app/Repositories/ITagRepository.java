package com.coffeecon.app.Repositories;

import com.coffeecon.app.Models.Tag;

import java.util.List;
import java.util.Optional;

public interface ITagRepository {

    /**
     * Create a tag
     * @param tag
     */
    public Tag save(Tag tag);

    /**
     * Get a Tag by id
     * @param id the id of the Tag
     * @return the Tag
     */
    public Optional<Tag> getById(int id);
   
    /**
     * Get all Tags
     * @return list off all Tags
     */
    public List<Tag> getAll();

    /**
     * Update a tag
     * @param tag
     */
    public Tag update(Tag tag);

    /**
     * Update a tag
     * @param id the id of the Tag
     */
    public int delete(int id);

}