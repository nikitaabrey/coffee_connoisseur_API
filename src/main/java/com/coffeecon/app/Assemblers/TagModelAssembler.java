package com.coffeecon.app.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.coffeecon.app.Models.Tag;
import com.coffeecon.app.controllers.TagController;

@Component
public class TagModelAssembler implements RepresentationModelAssembler<Tag, EntityModel<Tag>>{

    @Override
    public EntityModel<Tag> toModel(Tag tag) {

        return EntityModel.of(tag, //
            linkTo(methodOn(TagController.class).getTagById(tag.getTagId())).withSelfRel(),
            linkTo(methodOn(TagController.class).getTags()).withRel("tags"));
    }
    
}
