package com.sprints.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sprints.model.SprintModel;
import com.sprints.services.SprintsService;

@RestController
@RequestMapping(value = "/sprints")
public class SprintsController {
	
	@Autowired
	private SprintsService sprintsService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET)
	public List<SprintModel> showAllSprints(){
		return sprintsService.listAllSprints();
	}
}
