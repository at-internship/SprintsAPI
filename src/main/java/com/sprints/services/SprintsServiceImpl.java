package com.sprints.services;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sprints.domain.SprintDomain;
import com.sprints.exception.EntityConflictException;
import com.sprints.exception.EntityNotFoundException;
import com.sprints.mapper.SprintsDefaultMapper;
import com.sprints.mapper.SprintsTransformer;
import com.sprints.model.Sprint;
import com.sprints.repository.SprintsRepository;
import com.sprints.repository.SprintsCustomRepository;
import com.sprints.validations.SprintsValidations;

import io.swagger.annotations.ApiOperation;


@Service("sprintServiceImpl")
public class SprintsServiceImpl implements SprintsService {
	@Autowired
	private SprintsRepository sprintsRepository;
	
	@Autowired
	private SprintsTransformer sprintsTransformer;
	
	@Autowired
	private SprintsDefaultMapper sprintDefault;
	
	@Autowired
	private SprintsValidations sprintsValidations;
	
	@Autowired
	private SprintsCustomRepository sprintsValidationsRepository;
	
	//Get operation
	@ApiOperation(value = "Find sprints by id")
	@Override
	public SprintDomain findById(String id){
		if(sprintsRepository.existsById(id)) {
			Optional<Sprint> sprintOptional = sprintsRepository.findById(id);
			return sprintsTransformer.transformer(sprintOptional.get());
		}else {
			throw new EntityNotFoundException("This Sprint does not exist");
		}		
	}
	
	//Delete operation
	@ApiOperation(value = "Delete sprints by id")
	@Override
	public void deleteById(String id) {
		if(sprintsRepository.existsById(id)) {
			sprintsRepository.deleteById(id);
			return;
		}
		throw new EntityNotFoundException("The given ID could not be found");
	}
	
	//Get operation find all sprints
	@ApiOperation(value = "Find all sprints")
	@Override
	public List<SprintDomain> findAll() {
		List<Sprint> sprints = sprintsRepository.findAll();
		return sprintsTransformer.listTransformer(sprints);
	}
	
	//Post operation
	@ApiOperation(value = "Add new sprints")
	@Override
	@ResponseStatus(value = HttpStatus.CREATED)
	public String createSprint(SprintDomain sprintDomain) {
		
		SprintDomain sprintFinal = sprintDefault.sprintsDefaultValues(sprintDomain);
		
		if(sprintFinal.getActive() == true) {
			Sprint sprints = sprintsValidationsRepository.oneSprintActiveValidation();
			sprintsValidations.sprintsValidationsActive(sprints);
		}
		if(sprintFinal.getIs_backlog() == true) {
			Sprint sprint = sprintsValidationsRepository.oneSprintBacklogValidation();
			sprintsValidations.sprintValidateInBacklog(sprint);
		}
		try {
			return sprintsRepository.save(sprintsTransformer.transformer(sprintFinal)).getId().toString();
			
		}catch(DuplicateKeyException e) {
			throw new EntityConflictException("There is a sprint with this name already");
		}
	}
	
	// Put Operation
	@ApiOperation(value = "Update sprints by id")
		@Override
		@ResponseStatus(value = HttpStatus.ACCEPTED)
		public SprintDomain updateSprint(SprintDomain sprintDomain, String id) {
			if (sprintsRepository.existsById(id)) {

				SprintDomain sprintFinal = sprintDefault.sprintsDefaultValues(sprintDomain);
				try {
					sprintFinal.setId(id); 
					sprintsRepository.save(sprintsTransformer.transformer(sprintFinal));
					return sprintFinal;
				} catch (DuplicateKeyException e) {
					throw new EntityConflictException("There is a sprint with this name already");
				}

			} else {
				throw new EntityNotFoundException("The given ID could not be found");
			}

		}
	
}	
