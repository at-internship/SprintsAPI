package com.sprints.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sprints.domain.SprintDomain;
import com.sprints.exception.EntityConflictException;
import com.sprints.exception.EntityNotFoundException;
import com.sprints.mapper.SprintsDefaultMapper;
import com.sprints.mapper.SprintsTransformer;
import com.sprints.model.Sprint;
import com.sprints.repository.SprintsCustomRepository;
import com.sprints.repository.SprintsRepository;
import com.sprints.validations.SprintsValidations;

@Service("sprintServiceImpl")
public class SprintsServiceImpl implements SprintsService {
	@Autowired
	private SprintsRepository sprintsRepository;
	
	@Autowired
	private SprintsTransformer sprintsTransformer;
	
	@Autowired
	private SprintsDefaultMapper sprintDefault;
	
	@Autowired
	SprintsCustomRepository sprintsCustomRepository;
	
	private SprintsValidations sprintsValidations;
	
	@Autowired
	private SprintsCustomRepository sprintsValidationsRepository;
	
	//Get operation
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
	@Override
	public void deleteById(String id) {
		if(sprintsRepository.existsById(id)) {
			sprintsRepository.deleteById(id);
			return;
		}
		throw new EntityNotFoundException("The given ID could not be found");
	}
	
	//Get operation find all sprints
	@Override
	public List<SprintDomain> findAll() {
		List<Sprint> sprints = sprintsRepository.findAll();
		return sprintsTransformer.listTransformer(sprints);
	}
	
	//Post operation
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
		if(sprintDomain.getEnd_date() != null) {
			sprintsValidations.sprintsEndDateValidations(sprintDomain);
		}
		try {
			return sprintsRepository.save(sprintsTransformer.transformer(sprintFinal)).getId().toString();
			
		}catch(DuplicateKeyException e) {
			throw new EntityConflictException("There is a sprint with this name already");
		}
	}
	
	// Put Operation
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
		
	// Get Operation find all sprints sorted by parameters
		@Override
		public List<SprintDomain> findAllByParams(Optional<String> name, Optional<String> technology,
				Optional<LocalDate> start_date, Optional<LocalDate> end_date) {
			
			Criteria criteria = new Criteria();
			
			if(name.isPresent()) {
				criteria = criteria.and("name").is(name.get());
			}		
			if(technology.isPresent()) {
				criteria = criteria.and("technology").is(technology.get());
			}
			if(start_date.isPresent()) {
				LocalDate start_Date = start_date.get();
				Date startDate = Date.from(start_Date.atStartOfDay(ZoneId.of("UTC")).toInstant());
				
				criteria = criteria.and("start_date").is(startDate);
			}
			if(end_date.isPresent()) {
				LocalDate end_Date = end_date.get();
				Date endDate = Date.from(end_Date.atStartOfDay(ZoneId.of("UTC")).toInstant());
				
				criteria = criteria.and("end_date").is(endDate);
			}
			Query query = new Query(criteria);
			
			return sprintsTransformer.listTransformer(sprintsCustomRepository.findAllByParams(query));
		}
		
	//Method to check if GET with filters or GET ALL will be performed
		@Override
		public List<SprintDomain> findAllSprints(Optional<String> name, Optional<String> technology,
				Optional<LocalDate> start_date, Optional<LocalDate> end_date) {
				
			if(name.isEmpty() && technology.isEmpty() && start_date.isEmpty() && end_date.isEmpty()) {
				return findAll();
			}
				return findAllByParams(name, technology, start_date, end_date);
		}
	
}	
