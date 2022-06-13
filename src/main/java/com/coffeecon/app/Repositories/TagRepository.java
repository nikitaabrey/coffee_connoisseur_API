package com.coffeecon.app.Repositories;

import com.coffeecon.app.Models.Tag;
import com.coffeecon.app.Mappers.TagRowMapper;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepository implements ITagRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_TAG = "INSERT INTO Tag(Name,Description) VALUES (?,?)";
    private final String GET_ALL_TAGS = "SELECT * FROM Tag";
    private final String GET_SINGLE_TAG = "SELECT * FROM Tag WHERE TagID=?";
    private final String UPDATE_TAG = "UPDATE Tag SET name=?,description=? WHERE TagID=?";
    private final String DELETE_TAG = "DELETE FROM Tag WHERE TagID=?";

    @Override
    public int save(Tag tag) {
        
        return jdbcTemplate.update(INSERT_TAG, new Object[] { tag.getName(), tag.getDescription()});
        
    }
    @Override
    public Optional<Tag> getById(int id) {

        return Optional.of(jdbcTemplate.queryForObject(GET_SINGLE_TAG, new TagRowMapper(), new Object[] {id}));
    }
    @Override
    public List<Tag> getAll() {
        
        return jdbcTemplate.query(GET_ALL_TAGS, new TagRowMapper());
    }
    @Override
    public int update(Tag tag) {

        return jdbcTemplate.update(UPDATE_TAG, new Object[] { tag.getName(), tag.getDescription(), tag.getTagId() });
    }
    @Override
    public int delete(int id) {
        
        return jdbcTemplate.update(DELETE_TAG, new Object[] { id });
    }

     
    
    
}
