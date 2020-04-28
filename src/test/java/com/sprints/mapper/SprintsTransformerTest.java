package com.sprints.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sprints.domain.SprintDomain;
import com.sprints.model.Sprint;
import com.sprints.utils.TestUtils;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class SprintsTransformerTest {

	@InjectMocks
	private SprintsTransformer sprintsTransformer;

	@Mock
	private MapperFacade mapperFacade;

	private TestUtils testUtils;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		testUtils = new TestUtils();
		sprintsTransformer.setMapperFacade(new DefaultMapperFactory.Builder().build());
	}

	@Test
	public void testSprintTransformer() {
		doReturn(testUtils.getEmptySprint()).when(mapperFacade).map(TestUtils.getEmptySprintDomain(), Sprint.class);
		assertEquals(testUtils.getEmptySprint(), sprintsTransformer.transformer(TestUtils.getEmptySprintDomain()));
	}

	@Test
	public void testSprintDomainTransformer() {
		doReturn(TestUtils.getEmptySprintDomain()).when(mapperFacade).map(testUtils.getEmptySprint(),
				SprintDomain.class);
		assertEquals(TestUtils.getEmptySprintDomain(), sprintsTransformer.transformer(testUtils.getEmptySprint()));
	}

	@Test
	public void testListDomainTransformer() {
		doReturn(TestUtils.getEmptySprintDomainList()).when(mapperFacade).map(TestUtils.getEmptySprintList(),
				SprintDomain.class);
		assertEquals(TestUtils.getEmptySprintDomainList(),
				sprintsTransformer.listTransformer(TestUtils.getEmptySprintList()));
	}

}