package com.sprints.repository;

import java.util.List;
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
	public List<Sprint> findAllByParams(Criteria criteria) {
		
		Query query = new Query(criteria);	
		return mongoTemplate.find(query, Sprint.class);
	}

}