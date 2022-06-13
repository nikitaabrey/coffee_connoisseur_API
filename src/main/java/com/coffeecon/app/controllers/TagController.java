package com.coffeecon.app.controllers;

import com.coffeecon.app.Models.Tag;
import com.coffeecon.app.Services.TagService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping()
	public ResponseEntity<?> createUser(@RequestBody Tag tag) {

		tagService.createTag(tag);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

    @GetMapping("/{id}")
	public ResponseEntity<?> getTagById(@PathVariable("id") Integer id) {

		Tag tag = tagService.getTagById(id);
		return new ResponseEntity<>(tag, HttpStatus.OK);
	}

    @GetMapping()
	public ResponseEntity<?> getTags() {

		List<Tag> tags = tagService.getTags();
		return new ResponseEntity<>(tags, HttpStatus.OK);
	}

    @PutMapping()
	public ResponseEntity<?> updateTag(@RequestBody Tag tag) {

		tagService.updateTag(tag);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTag(@PathVariable("id") Integer id) {

		tagService.deleteTag(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    
}
