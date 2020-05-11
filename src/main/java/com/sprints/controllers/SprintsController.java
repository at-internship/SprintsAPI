package com.sprints.controllers;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sprints.domain.SprintDomain;
import com.sprints.domain.SprintDomainId;
import com.sprints.exception.EntityNotFoundException;
import com.sprints.services.SprintsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/sprints")
@Api(value = "SprintsController", description = "EndPoints for the Sprints API", tags = "/sprints")
public class SprintsController {

	@Autowired
	private SprintsService sprintsService;

	@ApiOperation(value = "DELETE Sprint", notes = "Delete a Sprint by id", tags = "/")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Sprint Not Found"),
			@ApiResponse(code = 409, message = "You cannot delete an active Sprint\"") })
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteSprintById(
			@ApiParam(value = "Id of the Sprint to be deleting, Cannot be empty.", required = true) @PathVariable String id) {
		sprintsService.deleteById(id);
	}

	@ApiOperation(value = "GET Sprint", notes = "Find a Sprint by id", response = SprintDomain.class, tags = "/")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Sprint Not Found") })
	@GetMapping("/{id}")
	public SprintDomain findSprintById(
			@ApiParam(value = "Id of the Sprint to be obtained, Cannot be empty.", required = true) @PathVariable("id") String id)
			throws EntityNotFoundException {
		SprintDomain sprintDomain = sprintsService.findById(id);
		return sprintDomain;
	}

	@ApiOperation(value = "GET Sprints", notes = "Finds all Sprints")
	@ApiResponse(code = 200, message = "OK")
	@RequestMapping(method = RequestMethod.GET)
	public List<SprintDomain> findAllSprints(
			@ApiParam(value = "Name of the Sprint.", required = false) @RequestParam("name") Optional<String> name,
			@ApiParam(value = "Technology that it's working on the Sprint.", required = false) @RequestParam("technology") Optional<String> technology,
			@ApiParam(value = "Date start work the Sprint.", required = false) @RequestParam("start_date") Optional<String> start_date,
			@ApiParam(value = "Deadline of the Sprint.", required = false) @RequestParam("end_date") Optional<String> end_date) {
		return sprintsService.findAllSprints(name, technology, start_date, end_date);

	}

	@ApiOperation(value = "POST Sprint", notes = "Add new Sprint", tags = "/")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "The JSON format provided is invalid"),
			@ApiResponse(code = 409, message = "Conflict") })
	@PostMapping(value = "/")
	public ResponseEntity<Object> createSprint(
			@ApiParam(value = "Add Sprint.", required = true) @Valid @RequestBody SprintDomain sprintDomain) {
		SprintDomainId sprintDomainId = new SprintDomainId();
		sprintDomainId.setId(sprintsService.createSprint(sprintDomain));
		return new ResponseEntity<>(sprintDomainId, HttpStatus.CREATED);

	}

	@ApiOperation(value = "PUT Sprint", notes = "Update an existing sprint by id", tags = "/")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "The JSON format provided is invalid"),
			@ApiResponse(code = 409, message = "Conflict"),
			@ApiResponse(code = 404, message = "Sprint Not Found") })
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping("/{id}")
	public SprintDomain updateSprint(
			@ApiParam(value = "Sprint's information to update.", required = true) @Valid @RequestBody SprintDomain sprintDomain,
			@ApiParam(value = "Id of the Sprint to be update, Cannot be empty.", required = true) @PathVariable String id) {
		return sprintsService.updateSprint(sprintDomain, id);

	}
}
