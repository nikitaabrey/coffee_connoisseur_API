package com.coffeecon.app.Repositories;

import com.coffeecon.app.Models.Tag;
import com.coffeecon.app.ExceptionHandlers.TagNotFoundException;
import com.coffeecon.app.Mappers.TagRowMapper;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    public Tag save(Tag tag) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_TAG, new String[] { "ID" });
            ps.setString(1, tag.getName());
            ps.setString(2, tag.getDescription());
            return ps;
          }, keyHolder);

        int tagId = keyHolder.getKey().intValue();
        tag.setTagId(tagId);
        return tag;
    }

    @Override
    public Optional<Tag> getById(int id) {

        try{
            return Optional.of(jdbcTemplate.queryForObject(GET_SINGLE_TAG, new TagRowMapper(), new Object[] {id}));
        }catch(EmptyResultDataAccessException e){
            throw new TagNotFoundException(id);
        }
    }

    @Override
    public List<Tag> getAll() {
        
        return jdbcTemplate.query(GET_ALL_TAGS, new TagRowMapper());
    }

    @Override
    public Tag update(Tag tag) {

        int status =  jdbcTemplate.update(UPDATE_TAG, new Object[] { tag.getName(), tag.getDescription(), tag.getTagId() });
        if(status == 0 ){
            throw new TagNotFoundException(tag.getTagId());
        }
        return tag;
    }

    @Override
    public int delete(int id) {
        
        return jdbcTemplate.update(DELETE_TAG, new Object[] { id });
    }

}
