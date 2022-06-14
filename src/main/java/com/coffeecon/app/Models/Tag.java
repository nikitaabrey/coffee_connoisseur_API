package com.coffeecon.app.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Tag {
    
    @Id
    private int tagId;
    private String name;
    private String description;

    public Tag(){

    }
    
    public Tag(int tagId, String name, String description){
        this.tagId = tagId;
        this.name = name;
        this.description = description;
    }

    public void setTagId(int tagId){
        this.tagId = tagId;
    }

    public int getTagId(){
        return this.tagId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
