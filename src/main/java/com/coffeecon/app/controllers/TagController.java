package com.coffeecon.app.controllers;

import com.coffeecon.app.Assemblers.TagModelAssembler;
import com.coffeecon.app.Models.Tag;
import com.coffeecon.app.Models.HttpResponseModels.HttpCreated;
import com.coffeecon.app.Models.HttpResponseModels.HttpSuccess;
import com.coffeecon.app.Services.TagService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<Object> newTag(@RequestBody Tag tag) {

        EntityModel<Tag> entityModel = tagAssembler.toModel(tagService.createTag(tag));

        return new HttpCreated.Builder<EntityModel<Tag>>(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .withBody(entityModel).build();
	}


    @GetMapping("/{id}")
	public ResponseEntity<Object> getTagById(@PathVariable("id") Integer id) {

        Tag tag = tagService.getTagById(id);
        EntityModel<Tag> entityModel = tagAssembler.toModel(tag);
        return new HttpSuccess.Builder<EntityModel<Tag>>(HttpStatus.OK)
                .withBody(entityModel).build();
	}

    @GetMapping()
	public ResponseEntity<Object>  getTags() {

		List<EntityModel<Tag>> tags = tagService.getTags().stream()
            .map(tagAssembler::toModel)
            .collect(Collectors.toList());
        CollectionModel<EntityModel<Tag>> collectionModel = CollectionModel.of(tags,
        linkTo(methodOn(TagController.class).getTags()).withSelfRel());

        return new HttpSuccess.Builder<CollectionModel<EntityModel<Tag>>>(HttpStatus.OK)
                .withBody(collectionModel).build();
	}


    @PutMapping("/{id}")
	public ResponseEntity<Object> updateTag(@RequestBody Tag newTag, @PathVariable int id) {

        EntityModel<Tag> entityModel = tagAssembler.toModel(tagService.updateTag(newTag, id));
       
        return new HttpCreated.Builder<EntityModel<Tag>>(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .withBody(entityModel).build();
	}


    @DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTag(@PathVariable("id") Integer id) {

		tagService.deleteTag(id);
        return new HttpSuccess.Builder<ResponseEntity<?>>(HttpStatus.NO_CONTENT).build();
	}

}
