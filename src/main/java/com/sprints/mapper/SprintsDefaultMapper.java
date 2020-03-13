package com.sprints.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.sprints.domain.SprintDomain;

@Component
public class SprintsDefaultMapper {
	
	public SprintDomain sprintsDefaultValues(SprintDomain sprintDomain) {
		if(sprintDomain.getActive() == null) {
			sprintDomain.setActive(false);
		}
		if(sprintDomain.getIs_backlog() == null) {
			sprintDomain.setIs_backlog(false);
		}
		if(sprintDomain.getStart_date() == null) {
			sprintDomain.setStart_date(LocalDate.now());
		}
		return sprintDomain;
	}
}
