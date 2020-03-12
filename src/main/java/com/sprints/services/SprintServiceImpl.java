package com.sprints.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprints.exception.EntityNotFoundException;
import com.sprints.model.Sprint;
import com.sprints.repository.SprintsRepository;


@Service("sprintServiceImpl")
public class SprintServiceImpl implements SprintsService {
	@Autowired
	private SprintsRepository sprintsRepository;
	
	@Override
	public Sprint findSprintById(String id){
		if(sprintsRepository.existsById(id)) {
			Optional<Sprint> sprintOptional = sprintsRepository.findById(id);
			return sprintOptional.get();
		}else {
			throw new EntityNotFoundException("This Sprint does not exist");
		}		
	}
	
	//Delete operation
	public void deleteById(String id) {
		if(sprintsRepository.existsById(id)) {
			sprintsRepository.deleteById(id);
			return;
		}
		throw new EntityNotFoundException("The given ID could not be found");
	}
}	
