package com.sprints.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.sprints.model.Sprint;

public interface SprintsCustomRepository {
	
	public Sprint oneSprintActiveValidation();
	
	public Sprint oneSprintBacklogValidation();
	
	public List<Sprint> findAllByParams(Query query);

}

