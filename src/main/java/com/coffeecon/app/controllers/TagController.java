package com.coffeecon.app.controllers;

import com.coffeecon.app.Assemblers.TagModelAssembler;
import com.coffeecon.app.Models.Tag;
import com.coffeecon.app.Services.TagService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;
    @Autowired
    private TagModelAssembler tagAssembler;


    @PostMapping()
	public ResponseEntity<?> newTag(@RequestBody Tag tag) {

        EntityModel<Tag> entityModel = tagAssembler.toModel(tagService.createTag(tag));
		
        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);

	}


    @GetMapping("/{id}")
	public EntityModel<Tag> getTagById(@PathVariable("id") Integer id) {

        Tag tag = tagService.getTagById(id);
        return tagAssembler.toModel(tag);
	}


    @GetMapping()
	public CollectionModel<EntityModel<Tag>> getTags() {

		List<EntityModel<Tag>> tags = tagService.getTags().stream()
            .map(tagAssembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(tags,
        linkTo(methodOn(TagController.class).getTags()).withSelfRel());

	}


    @PutMapping("/{id}")
	public ResponseEntity<?> updateTag(@RequestBody Tag newTag, @PathVariable int id) {

        EntityModel<Tag> entityModel = tagAssembler.toModel(tagService.updateTag(newTag, id));
        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
	}


    @DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTag(@PathVariable("id") Integer id) {

		tagService.deleteTag(id);
		return ResponseEntity.noContent().build();
	}

}
