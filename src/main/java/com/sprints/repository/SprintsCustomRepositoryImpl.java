package com.sprints.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sprints.model.Sprint;

@Repository
public class SprintsCustomRepositoryImpl implements SprintsCustomRepository{
	
	private MongoTemplate mongoTemplate;
	
	public SprintsCustomRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public Sprint oneSprintActiveValidation() {
		Query query = new Query()
				.addCriteria(Criteria.where("active").is(true));
		return mongoTemplate.findOne(query, Sprint.class);
	}
	
	@Override
	public Sprint oneSprintBacklogValidation() {
		Query query = new Query()
						.addCriteria(Criteria.where("is_backlog").is(true));
		return mongoTemplate.findOne(query, Sprint.class);
	}

	@Override
	public List<Sprint> findAllByParams(Optional<String> name, Optional<String> technology,
			Optional<LocalDate> start_date, Optional<LocalDate> end_date) {
		
		Criteria criteria = new Criteria();
		
		if(name.isPresent()) {
			criteria = criteria.and("name").is(name.get());
		}		
		if(technology.isPresent()) {
			criteria = criteria.and("technology").is(technology.get());
		}
		if(start_date.isPresent()) {
			criteria = criteria.and("start_date").is(start_date.get());
		}
		if(end_date.isPresent()) {
			criteria = criteria.and("end_date").is(end_date.get());
		}
		
		Query query = new Query(criteria);
		
		return mongoTemplate.find(query, Sprint.class);
	}
	
}