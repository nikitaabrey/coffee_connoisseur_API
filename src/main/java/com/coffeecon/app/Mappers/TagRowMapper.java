package com.coffeecon.app.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coffeecon.app.Models.Tag;

public class TagRowMapper implements RowMapper<Tag>{

    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException{
        
        Tag tag = new Tag();
        tag.setTagId(rs.getInt("TagID"));
        tag.setName(rs.getString("Name"));
        tag.setDescription(rs.getString("Description"));

        return tag;
    }
    
}
