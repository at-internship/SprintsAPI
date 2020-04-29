package com.sprints.validations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sprints.utils.SprintsConstants;
import com.sprints.utils.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
public class ValidateQueryParamsTest {

	@InjectMocks
	private ValidateQueryParams validateQueryParams;

	private SprintsConstants sprintsConstants;

	@Mock
	ValidateDateFormat validateDateFormat;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		sprintsConstants = new SprintsConstants();
	}

	@Test
	public void testFillCriteriaWithParams() {
		when(validateDateFormat.stringToDate(sprintsConstants.getCriteriaStartD()))
				.thenReturn(sprintsConstants.getDate());
		when(validateDateFormat.stringToDate(sprintsConstants.getCriteriaEndD()))
				.thenReturn(sprintsConstants.getDate());
		assertEquals(sprintsConstants.getCriteria1(),
				validateQueryParams.fillCriteriaWithParams(sprintsConstants.getCriteriaName(),
						sprintsConstants.getCriteriaTechnology(), sprintsConstants.getCriteriaStartD(),
						sprintsConstants.getCriteriaEndD()));
	}

	@Test
	public void testFillCriteriaWithParamsFailed() {
		assertEquals(TestUtils.getEmptyCriteria(),
				validateQueryParams.fillCriteriaWithParams(sprintsConstants.getCriteriaNameEmpty(),
						sprintsConstants.getCriteriaTechnologyEmpty(), sprintsConstants.getCriteriaStartDEmpty(),
						sprintsConstants.getCriteriaEndDEmpty()));
	}

	@Test
	public void testFillCriteriaWithParamsWrong() {
		assertEquals(sprintsConstants.getCriteriaNoDates(),
				validateQueryParams.fillCriteriaWithParams(sprintsConstants.getCriteriaName(),
						sprintsConstants.getCriteriaTechnology(), sprintsConstants.getCriteriaNoStartDate(),
						sprintsConstants.getCriteriaNoEndDate()));
	}

	@Test
	public void testFillCriteriaBlanks() {
		assertEquals(TestUtils.getEmptyCriteria(),
				validateQueryParams.fillCriteriaWithParams(sprintsConstants.getCriteriaNameBlank(),
						sprintsConstants.getCriteriaTechnologyBlank(), sprintsConstants.getCriteriaStartDateBlank(),
						sprintsConstants.getCriteriaEndDateBlank()));
	}
}
