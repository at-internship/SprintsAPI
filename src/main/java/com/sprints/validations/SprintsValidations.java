package com.sprints.validations;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.sprints.domain.SprintDomain;
import com.sprints.exception.BadRequestException;
import com.sprints.exception.EntityConflictException;
import com.sprints.model.Sprint;

@Component
public class SprintsValidations {
	
	public void sprintValidateBothBooleans(SprintDomain sprintDomain){
			if(sprintDomain.getActive() && sprintDomain.getIs_backlog()) {
				throw new EntityConflictException("A sprint cannot be Active and in Backlog at the same time", "/sprints/");
		}
	}
	
	public void sprintValidateBothBooleans(SprintDomain sprintDomain, String id){
		if(sprintDomain.getActive() && sprintDomain.getIs_backlog()) {
			throw new EntityConflictException("A sprint cannot be Active and in Backlog at the same time", "/sprints/" + id);
		}
	}	
	
	public void sprintsValidationsActive(Sprint sprint) {
		if(sprint != null)
			if(sprint.getActive() == true) {
				throw new EntityConflictException("There is an active sprint already", "/sprints/");
			}
	}
	
	public void sprintsValidationsActive(Sprint sprint, String id) {
		if(sprint != null)
			if(sprint.getActive() == true) {
				throw new EntityConflictException("There is an active sprint already", "/sprints/" + id);
			}
	}

	public void sprintValidateInBacklog(Sprint sprint){
		if (sprint != null) {
			if(sprint.getIs_backlog() == true) {
				throw new EntityConflictException("There is a sprint in backlog already", "/sprints/");
			}
		}
	}
	
	public void sprintValidateInBacklog(Sprint sprint, String id){
		if (sprint != null) {
			if(sprint.getIs_backlog() == true) {
				throw new EntityConflictException("There is a sprint in backlog already", "/sprints/" + id);
			}
		}
	}
	
	public void sprintsEndDateValidations(SprintDomain sprintDomain) {
		if(sprintDomain.getActive() == true) {
			if(sprintDomain.getEnd_date().isBefore(LocalDate.now()) || sprintDomain.getEnd_date().isEqual(LocalDate.now())) {
				throw new EntityConflictException("Sprints with past/present date cannot be active", "/sprints/");
			}
		}
	}
	
	public void sprintsEndDateValidations(SprintDomain sprintDomain, String id) {
		if(sprintDomain.getActive() == true) {
			if(sprintDomain.getEnd_date().isBefore(LocalDate.now()) || sprintDomain.getEnd_date().isEqual(LocalDate.now())) {
				throw new EntityConflictException("Sprints with past/present date cannot be active", "/sprints/" + id);
			}
		}
	}
	
	public void sprintsActiveValidation(Optional<Sprint> sprintOptional, String id){
		if(sprintOptional.get().getActive() == true) {
			throw new EntityConflictException("You cannot delete an active sprint", "/sprints/" + id);
		}
	}
	
	public void sprintsNameValidations(SprintDomain sprintDomain) {
		if(sprintDomain.getName() == null || sprintDomain.getName().isEmpty() || sprintDomain.getName().chars().allMatch(Character::isWhitespace)) {
			throw new BadRequestException("The JSON format provided is invalid, please provide the required field ('Name')", "/sprints/");
		}
	}
	
	public void sprintsNameValidations(SprintDomain sprintDomain, String id) {
		if(sprintDomain.getName() == null || sprintDomain.getName().isEmpty() || sprintDomain.getName().chars().allMatch(Character::isWhitespace)) {
			throw new BadRequestException("The JSON format provided is invalid, please provide the required field ('Name')", "/sprints/" + id);
		}
	}
	
	public void sprintValidateStartDate(SprintDomain sprintFinal){
		if((sprintFinal.getStart_date().isAfter(sprintFinal.getEnd_date())) || (sprintFinal.getStart_date().isEqual(sprintFinal.getEnd_date()))) {
			throw new EntityConflictException("The start date cannot be greater or same than the end date","/sprints/");
		}
	}
	
	public void sprintValidateStartDate(SprintDomain sprintFinal, String id){
		if((sprintFinal.getStart_date().isAfter(sprintFinal.getEnd_date())) || (sprintFinal.getStart_date().isEqual(sprintFinal.getEnd_date()))) {
			throw new EntityConflictException("The start date cannot be greater or same than the end date","/sprints/" + id);
		}
	}
}
