package com.sprints.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.sprints.model.Sprint;
import com.sprints.utils.SprintsConstants;
import com.sprints.utils.TestUtils;

public class SprintsCustomRepositoryImplTest {

	@InjectMocks
	private SprintsCustomRepositoryImpl sprintsCustomRepositoryImpl;

	@Mock
	private SprintsRepository sprintsRepository;

	@Mock
	private MongoTemplate mongoTemplate;

	private SprintsConstants sprintsConstants;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		sprintsConstants = new SprintsConstants();
		sprintsRepository.save(TestUtils.getDummySprintTrue());
	}

	@Test
	public void testOneSprintActiveValidation() {
		Query query = new Query().addCriteria(Criteria.where("active").is(sprintsConstants.isBooleanTrue()));
		doReturn(TestUtils.getDummySprintTrue()).when(mongoTemplate).findOne(query, Sprint.class);
		assertEquals(TestUtils.getDummySprintTrue(), sprintsCustomRepositoryImpl.oneSprintActiveValidation());
	}

	@Test
	public void testOneSprintBacklogValidation() {
		Query query = new Query().addCriteria(Criteria.where("is_backlog").is(sprintsConstants.isBooleanTrue()));
		doReturn(TestUtils.getDummySprintTrue()).when(mongoTemplate).findOne(query, Sprint.class);
		assertEquals(TestUtils.getDummySprintTrue(), sprintsCustomRepositoryImpl.oneSprintBacklogValidation());
	}

	@Test
	public void testFindAllByParamsName() {
		Criteria criteria = new Criteria();
		criteria = criteria.and(sprintsConstants.getStringName()).is(sprintsConstants.getCriteriaName());
		Query query = new Query(criteria);
		doReturn(TestUtils.getSomeSprintsList()).when(mongoTemplate).find(query, Sprint.class);
		assertEquals(TestUtils.getSomeSprintsList(), sprintsCustomRepositoryImpl.findAllByParams(criteria));
	}
}
