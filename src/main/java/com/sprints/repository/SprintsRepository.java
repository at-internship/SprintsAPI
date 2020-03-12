package com.sprints.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sprints.model.Sprint;

@Repository
public interface SprintsRepository extends MongoRepository<Sprint, String> {
	
	public Optional<Sprint> findById(String id);
}
