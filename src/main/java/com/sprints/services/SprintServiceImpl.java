package com.sprints.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sprints.component.SprintConverter;
import com.sprints.model.Sprint;
import com.sprints.model.SprintModel;
import com.sprints.repository.SprintsRepository;

@Service("sprintServiceImpl")
public class SprintServiceImpl implements SprintsService {
	
	@Autowired
	private SprintsRepository sprintRepository;
	
	@Autowired
	@Qualifier("sprintConverter")
	private SprintConverter sprintConverter;
	
	
	@Override
	public List<SprintModel> listAllSprints() {
		List<Sprint> sprints = sprintRepository.findAll();
		List<SprintModel> sprintsModel = new ArrayList<SprintModel>();
		for(Sprint sprint : sprints) {
			sprintsModel.add(sprintConverter.convertSprint2SprintModel(sprint));
		}
		return sprintsModel;
	}

}












