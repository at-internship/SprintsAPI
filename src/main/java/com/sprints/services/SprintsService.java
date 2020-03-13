package com.sprints.services;

import java.util.List;

import com.sprints.domain.SprintDomain;
import com.sprints.model.Sprint;

public interface SprintsService {
	
	public SprintDomain findById(String id);
	
	public void deleteById(String id);
	
	public abstract List<SprintDomain> findAll();
	
}