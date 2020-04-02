package com.sprints.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sprints.domain.SprintDomain;
import com.sprints.domain.SprintDomainId;
import com.sprints.exception.EntityNotFoundException;
import com.sprints.interceptors.DisallowUndeclaredRequestParams;
import com.sprints.services.SprintsService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/sprints")
public class SprintsController {

	@Autowired
	private SprintsService sprintsService;
	
	@ApiOperation(value = "Delete sprints by id")
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteSprintById(@PathVariable String id) {
			sprintsService.deleteById(id);
	}
	
	@ApiOperation(value = "Find sprints by id")
	@GetMapping("/{id}")
	public SprintDomain findSprintById(@PathVariable("id") String id) throws EntityNotFoundException {
		SprintDomain sprintDomain = sprintsService.findById(id);
		return sprintDomain;
	}
	
	@ApiOperation(value = "Find all sprints")
	@RequestMapping(method=RequestMethod.GET)
	@DisallowUndeclaredRequestParams
	public List<SprintDomain> findAllSprints( @RequestParam("name") Optional<String> name,
												@RequestParam("technology") Optional<String> technology,
												@RequestParam("start_date") @DateTimeFormat(iso = ISO.DATE) Optional<LocalDate> start_date,
												@RequestParam("end_date") @DateTimeFormat(iso = ISO.DATE) Optional<LocalDate> end_date){
		
		return sprintsService.findAllSprints(name, technology, start_date, end_date);

	}
	
	
	@ApiOperation(value = "Add new sprints")
	@PostMapping(value = "/")
	public ResponseEntity<Object> createSprint(@Valid @RequestBody SprintDomain sprintDomain){

		SprintDomainId sprintDomainId = new SprintDomainId();
		sprintDomainId.setId(sprintsService.createSprint(sprintDomain));
		return new ResponseEntity<>(sprintDomainId, HttpStatus.CREATED);
					
	}
	
	@ApiOperation(value = "Update sprints by id")
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping("/{id}")
	public SprintDomain updateSprint(@Valid @RequestBody SprintDomain sprintDomain, @PathVariable String id) {
		return sprintsService.updateSprint(sprintDomain, id);

	}
}
