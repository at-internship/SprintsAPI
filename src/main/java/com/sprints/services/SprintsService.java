package com.sprints.services;

import com.sprints.model.Sprint;

public interface SprintsService {
	
	public Sprint findById(String id);
	
	public void deleteById(String id);
	
}