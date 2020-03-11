package com.sprints.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprints.repository.SprintsRepository;

@Service
public class SprintsService {

	@Autowired
	private SprintsRepository sprintsRepository;
	
	//Delete operation
	public void deleteById(String id) {
		sprintsRepository.deleteById(id);
	}
	public boolean existsById(String id) {
		if(sprintsRepository.existsById(id)) {
			return true;
		}
		return false;
	}
}
