package com.sprints.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.query.Criteria;
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
import com.sprints.validations.ValidateQueryParams;

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

	@Autowired
	private SprintsValidations sprintsValidations;

	@Autowired
	private SprintsCustomRepository sprintsValidationsRepository;

	@Autowired
	private ValidateQueryParams validateQueryParams;

	final static Logger logger = LogManager.getLogger(SprintsServiceImpl.class);

	// Get operation
	@Override
	public SprintDomain findById(String id) {
		if (sprintsRepository.existsById(id)) {
			logger.info("Searching Sprint with id: " + id);
			Optional<Sprint> sprintOptional = sprintsRepository.findById(id);
			logger.info("Sprint found");
			return sprintsTransformer.transformer(sprintOptional.get());
		} else {
			logger.error("Sprint Not Found " + id);
			throw new EntityNotFoundException("Sprint not found", "/sprints/" + id);
		}
	}

	// Delete operation
	@Override
	public void deleteById(String id) {
		if (sprintsRepository.existsById(id)) {
			logger.info("Searching Sprint with id: " + id);
			Optional<Sprint> sprintOptional = sprintsRepository.findById(id);
			logger.info("Validating if the Sprint is active");
			sprintsValidations.sprintsActiveValidation(sprintOptional, id);
			sprintsRepository.deleteById(id);
			logger.info("Sprint removed");
			return;
		}
		logger.error("Sprint Not Found " + id);
		throw new EntityNotFoundException("Sprint not found", "/sprints/" + id);
	}

	// Get operation find all sprints
	@Override
	public List<SprintDomain> findAll() {
		logger.info("Searching all Sprints");
		List<Sprint> sprints = sprintsRepository.findAll();
		return sprintsTransformer.listTransformer(sprints);
	}

	// Post operation
	@Override
	@ResponseStatus(value = HttpStatus.CREATED)
	public String createSprint(SprintDomain sprintDomain) {

		SprintDomain sprintFinal = sprintDefault.sprintsDefaultValues(sprintDomain);
		logger.info("Creating Sprint");
		sprintsValidations.sprintValidateBothBooleans(sprintDomain);

		sprintsValidations.sprintsNameValidations(sprintDomain);

		if (sprintFinal.getActive() == true) {
			logger.info("Validating if there is any Sprint active");
			Sprint sprints = sprintsValidationsRepository.oneSprintActiveValidation();
			sprintsValidations.sprintsValidationsActive(sprints);
		}

		if (sprintFinal.getIs_backlog() == true) {
			logger.info("Validating if there is any Sprint in backlog");
			Sprint sprint = sprintsValidationsRepository.oneSprintBacklogValidation();
			sprintsValidations.sprintValidateInBacklog(sprint);
		}

		if (sprintDomain.getEnd_date() != null) {
			logger.info("Validating the end date");
			sprintsValidations.sprintValidateStartDate(sprintDomain);
			sprintsValidations.sprintsEndDateValidations(sprintDomain);
		}
		try {
			logger.info("Sprint created");
			return sprintsRepository.save(sprintsTransformer.transformer(sprintFinal)).getId().toString();

		} catch (DuplicateKeyException e) {
			logger.error("There is a sprint with this name already: " + sprintFinal.getName());
			throw new EntityConflictException("There is a sprint with this name already", "/sprints/");
		}
	}

	// Put Operation
	@Override
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public SprintDomain updateSprint(SprintDomain sprintDomain, String id) {
		if (sprintsRepository.existsById(id)) {

			SprintDomain sprintFinal = sprintDefault.sprintsDefaultValues(sprintDomain);
			logger.info("Updating Sprint");
			sprintsValidations.sprintValidateBothBooleans(sprintDomain, id);

			sprintsValidations.sprintsNameValidations(sprintDomain, id);

			if (sprintFinal.getActive() == true) {
				logger.info("Validating if there is any Sprint active");
				Sprint sprints = sprintsValidationsRepository.oneSprintActiveValidation();
				sprintsValidations.sprintsValidationsActive(sprints, id);
			}

			if (sprintFinal.getIs_backlog() == true) {
				logger.info("Validating if there is any Sprint in backlog");
				Sprint sprint = sprintsValidationsRepository.oneSprintBacklogValidation();
				sprintsValidations.sprintValidateInBacklog(sprint, id);
			}

			if (sprintFinal.getEnd_date() != null) {
				logger.info("Validating the end date");
				sprintsValidations.sprintValidateStartDate(sprintDomain, id);
				sprintsValidations.sprintsEndDateValidations(sprintDomain, id);
			}

			try {
				sprintFinal.setId(id);
				sprintsRepository.save(sprintsTransformer.transformer(sprintFinal));
				logger.info("Sprint updated");
				return sprintFinal;
			} catch (DuplicateKeyException e) {
				logger.error("There is a sprint with this name already: " + sprintFinal.getName());
				throw new EntityConflictException("There is a sprint with this name already", "/sprints/" + id);
			}

		} else {
			logger.error("Sprint Not Found " + id);
			throw new EntityNotFoundException("Sprint not found", "/sprints/" + id);
		}

	}

	// Get Operation find all sprints sorted by parameters
	@Override
	public List<SprintDomain> findAllByParams(Optional<String> name, Optional<String> technology,
			Optional<String> start_date, Optional<String> end_date) {

		Criteria criteria = validateQueryParams.fillCriteriaWithParams(name, technology, start_date, end_date);
		logger.info("Serach the parameter");
		return sprintsTransformer.listTransformer(sprintsCustomRepository.findAllByParams(criteria));
	}

	// Method to check if GET with filters or GET ALL will be performed
	@Override
	public List<SprintDomain> findAllSprints(Optional<String> name, Optional<String> technology,
			Optional<String> start_date, Optional<String> end_date) {
		logger.info("Validating search");
		if (!name.isPresent() && !technology.isPresent() && !start_date.isPresent() && !end_date.isPresent()) {
			return findAll();
		}
		logger.info("Searching by parameter, name: " + name + " technology: " + technology + " Start Date: "
				+ start_date + " End date: " + end_date);
		return findAllByParams(name, technology, start_date, end_date);
	}
	
	public void testingConflict() {
		logger.info("Método de Luis");
	}

}
