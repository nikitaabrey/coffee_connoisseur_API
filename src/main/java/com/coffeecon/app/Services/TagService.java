package com.coffeecon.app.Services;

import com.coffeecon.app.Models.Tag;
import com.coffeecon.app.Repositories.TagRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepo;

    public void createTag(Tag tag){
        tagRepo.save(tag);
    }

    public List<Tag> getTags(){
        return tagRepo.getAll();
    }

    public Tag getTagById(int id){
        Tag tag = new Tag();
        Optional<Tag> existingTag = tagRepo.getById(id);
        if(existingTag.isPresent()){
            tag = existingTag.get();
        }
        return tag;
    }

    public void updateTag(Tag tag){
        tagRepo.update(tag);
    }

    public void deleteTag(int id) {
		tagRepo.delete(id);
	}

    
}
