package com.sprints.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprints.exception.EntityNotFoundException;
import com.sprints.model.Sprint;
import com.sprints.services.SprintsService;

@RestController
@RequestMapping(value = "/sprints")
public class SprintsController {

	@Autowired
	private SprintsService sprintsService;
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Sprint> deleteSprintById(@PathVariable String id) {
			if(sprintsService.existsById(id)) {
				sprintsService.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				throw new EntityNotFoundException("");
			}
			
	}

}
