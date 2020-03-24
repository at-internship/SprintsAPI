package com.sprints.controllers;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sprints.domain.SprintDomain;
import com.sprints.exception.EntityNotFoundException;
import com.sprints.model.Sprint;
import com.sprints.services.SprintsService;

@RestController
@RequestMapping(value = "/sprints")
public class SprintsController {

	@Autowired
	private SprintsService sprintsService;
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteSprintById(@PathVariable String id) {
			sprintsService.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public SprintDomain findSprintById(@PathVariable("id") String id) throws EntityNotFoundException {
		SprintDomain sprintDomain = sprintsService.findById(id);
		return sprintDomain;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<SprintDomain> findAllSprints(){
		return sprintsService.findAll();
	}
	
	@PostMapping(value = "/")
	public ResponseEntity<Object> createSprint(@Valid @RequestBody SprintDomain sprintDomain){

				String id = sprintsService.createSprint(sprintDomain);
				return new ResponseEntity<>("{"+"\"id\""+": " + id + "}", HttpStatus.CREATED);
					
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping("/{id}")
	public SprintDomain updateSprint(@Valid @RequestBody SprintDomain sprintDomain, @PathVariable String id) {
		return sprintsService.updateSprint(sprintDomain, id);

	}
}
