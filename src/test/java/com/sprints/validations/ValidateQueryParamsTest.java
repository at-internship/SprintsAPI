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
		when(validateDateFormat.stringToDate(sprintsConstants.getCRITERIA_START_D()))
				.thenReturn(sprintsConstants.getDATE());
		when(validateDateFormat.stringToDate(sprintsConstants.getCRITERIA_END_D()))
				.thenReturn(sprintsConstants.getDATE());
		assertEquals(sprintsConstants.getCRITERIA_1(),
				validateQueryParams.fillCriteriaWithParams(sprintsConstants.getCRITERIA_NAME(),
						sprintsConstants.getCRITERIA_TECHNOLOGY(), sprintsConstants.getCRITERIA_START_D(),
						sprintsConstants.getCRITERIA_END_D()));
	}

	@Test
	public void testFillCriteriaWithParamsFailed() {
		assertEquals(TestUtils.getEmptyCriteria(),
				validateQueryParams.fillCriteriaWithParams(sprintsConstants.getCRITERIA_NAME_EMPTY(),
						sprintsConstants.getCRITERIA_TECHNOLOGY_EMPTY(), sprintsConstants.getCRITERIA_START_D_EMPTY(),
						sprintsConstants.getCRITERIA_END_D_EMPTY()));
	}

	@Test
	public void testFillCriteriaWithParamsWrong() {
		assertEquals(sprintsConstants.getCRITERIA_NO_DATES(),
				validateQueryParams.fillCriteriaWithParams(sprintsConstants.getCRITERIA_NAME(),
						sprintsConstants.getCRITERIA_TECHNOLOGY(), sprintsConstants.getCRITERIA_NO_START_DATE(),
						sprintsConstants.getCRITERIA_NO_END_DATE()));
	}

	@Test
	public void testFillCriteriaBlanks() {
		assertEquals(TestUtils.getEmptyCriteria(),
				validateQueryParams.fillCriteriaWithParams(sprintsConstants.getCRITERIA_NAME_BLANK(),
						sprintsConstants.getCRITERIA_TECHNOLOGY_BLANK(), sprintsConstants.getCRITERIA_START_DATE_BLANK(),
						sprintsConstants.getCRITERIA_END_DATE_BLANK()));
	}
}
