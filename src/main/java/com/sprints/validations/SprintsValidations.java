package com.sprints.validations;

import org.springframework.stereotype.Component;
import com.sprints.exception.EntityConflictException;
import com.sprints.model.Sprint;

@Component
public class SprintsValidations {
	
	public void sprintsValidationsActive(Sprint sprint) {
			if(sprint.getActive() == true) {
				throw new EntityConflictException("There is an active sprint already");
			}
	}

	public void sprintValidateInBacklog(Sprint sprint){
		if (sprint != null) {
			if(sprint.getIs_backlog() == true) {
				throw new EntityConflictException("There is a sprint already in backlog");
			}
		}
	}
}
