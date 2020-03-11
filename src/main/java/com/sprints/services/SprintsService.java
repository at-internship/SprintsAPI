package com.sprints.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprints.exception.EntityNotFoundException;
import com.sprints.repository.SprintsRepository;

@Service
public class SprintsService {

	@Autowired
	private SprintsRepository sprintsRepository;
	
	//Delete operation
	public void deleteById(String id) {
		if(sprintsRepository.existsById(id)) {
			sprintsRepository.deleteById(id);
			return;
	}
		throw new EntityNotFoundException("The given ID could not be found");
	}
}
