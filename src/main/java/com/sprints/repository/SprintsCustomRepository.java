package com.sprints.repository;

import com.sprints.model.Sprint;

public interface SprintsCustomRepository {
	
	public Sprint oneSprintActiveValidation();
	
	public Sprint oneSprintBacklogValidation();

}
