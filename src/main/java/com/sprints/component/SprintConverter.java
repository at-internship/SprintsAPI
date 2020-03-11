package com.sprints.component;

import org.springframework.stereotype.Component;

import com.sprints.model.Sprint;
import com.sprints.model.SprintModel;

@Component("sprintConverter")
public class SprintConverter {

	public Sprint convertSprintModel2Sprint(SprintModel sprintModel) {
		Sprint sprint = new Sprint();
		sprint.setId(sprintModel.getId());
		sprint.setName(sprintModel.getName());
		sprint.setTechnology(sprintModel.getTechnology());
		sprint.setActive(sprintModel.getActive());
		sprint.setIs_backlog(sprintModel.getIs_backlog());
		sprint.setStart_date(sprintModel.getStart_date());
		sprint.setEnd_date(sprintModel.getEnd_date());
		return null;
		
	}
	
	public SprintModel convertSprint2SprintModel(Sprint sprint) {
		SprintModel sprintModel = new SprintModel();
		sprintModel.setId(sprint.getId());
		sprintModel.setName(sprint.getName());
		sprintModel.setTechnology(sprint.getTechnology());
		sprintModel.setActive(sprint.getActive());
		sprintModel.setIs_backlog(sprint.getIs_backlog());
		sprintModel.setStart_date(sprint.getStart_date());
		sprintModel.setEnd_date(sprint.getEnd_date());
		return sprintModel;
		
	}
	
}
