package com.sprints.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sprints.model.Sprint;

public interface SprintsCustomRepository {
	
	public Sprint oneSprintActiveValidation();
	
	public Sprint oneSprintBacklogValidation();
	
	public List<Sprint> findAllByParams(Optional<String> name, Optional<String> technology,
			Optional<LocalDate> start_date, Optional<LocalDate> end_date);

}

