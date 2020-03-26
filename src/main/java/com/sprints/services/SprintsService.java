package com.sprints.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sprints.domain.SprintDomain;

public interface SprintsService {
	
	public SprintDomain findById(String id);
	
	public void deleteById(String id);
	
	public abstract List<SprintDomain> findAll();
	
	public String createSprint(SprintDomain sprintDomain);
	
	public SprintDomain updateSprint(SprintDomain sprintDomain, String id);

	public List<SprintDomain> findAllByParams(Optional<String> name, Optional<String> technology,
			Optional<LocalDate> start_date, Optional<LocalDate> end_date);

	public List<SprintDomain> findAllSprints(Optional<String> name, Optional<String> technology,
			Optional<LocalDate> start_date, Optional<LocalDate> end_date);
}