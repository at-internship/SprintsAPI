package com.sprints.validations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sprints.exception.BadRequestException;
import com.sprints.exception.EntityConflictException;
import com.sprints.utils.SprintsConstants;
import com.sprints.utils.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
public class SprintsValidationsTest {

	@InjectMocks
	private SprintsValidations sprintValidations;

	private TestUtils testUtils;
	private SprintsConstants sprintsConstants;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		sprintsConstants = new SprintsConstants();
		testUtils = new TestUtils();
	}

	/***
	 * Sprint that validates Both Booleans
	 */
	@Test(expected = EntityConflictException.class)
	public void testSprintValidateBothBooleans() {
		sprintValidations.sprintValidateBothBooleans(testUtils.getSprintDomainTrue());
	}
	
	@Test(expected = EntityConflictException.class)
	public void testSprintValidateBothBooleansId() {
		sprintValidations.sprintValidateBothBooleans(testUtils.getSprintDomainTrue(), sprintsConstants.getSPRINT_ID());
	}

	/***
	 * This one test active is true
	 */
	@Test
	public void testSprintValidateBothBooleansActive() {
		sprintValidations.sprintValidateBothBooleans(testUtils.getSprintDomainActive());
	}
	
	@Test
	public void testSprintValidateBothBooleansActiveId() {
		sprintValidations.sprintValidateBothBooleans(testUtils.getSprintDomainActive(), sprintsConstants.getSPRINT_ID());
	}

	/***
	 * This one test backlog is true
	 */
	@Test
	public void testSprintValidateBothBooleansBacklog() {
		sprintValidations.sprintValidateBothBooleans(testUtils.getSprintDomainBacklog());
	}
	
	@Test
	public void testSprintValidateBothBooleansBacklogId() {
		sprintValidations.sprintValidateBothBooleans(testUtils.getSprintDomainBacklog(), sprintsConstants.getSPRINT_ID());
	}

	/***
	 * This one test active and backlog are false
	 */
	@Test
	public void testSprintValidateBothBooleansFalse() {
		sprintValidations.sprintValidateBothBooleans(testUtils.getSprintDomain());
	}
	
	@Test
	public void testSprintValidateBothBooleansFalseId() {
		sprintValidations.sprintValidateBothBooleans(testUtils.getSprintDomain(), sprintsConstants.getSPRINT_ID());
	}

	/***
	 * Validation of active
	 */
	@Test(expected = EntityConflictException.class)
	public void testSprintsValidationsActive() {
		sprintValidations.sprintsValidationsActive(TestUtils.getSprint());
	}
	
	@Test(expected = EntityConflictException.class)
	public void testSprintsValidationsActiveId() {
		sprintValidations.sprintsValidationsActive(TestUtils.getSprint(), sprintsConstants.getSPRINT_ID());
	}

	@Test
	public void testSprintsValidationsActiveWrong() {
		sprintValidations.sprintsValidationsActive(null);
	}
	
	@Test
	public void testSprintsValidationsActiveWrongId() {
		sprintValidations.sprintsValidationsActive(null, sprintsConstants.getSPRINT_ID());
	}

	@Test
	public void testSprintsValidationsActiveFalse() {
		sprintValidations.sprintsValidationsActive(TestUtils.getSprintTwo());
	}
	
	@Test
	public void testSprintsValidationsActiveFalseId() {
		sprintValidations.sprintsValidationsActive(TestUtils.getSprintTwo(), sprintsConstants.getSPRINT_ID());
	}

	/***
	 * Validation of backlog
	 */
	@Test(expected = EntityConflictException.class)
	public void sprintValidateInBacklog() {
		sprintValidations.sprintValidateInBacklog(TestUtils.getSprintTwo());
	}
	
	@Test(expected = EntityConflictException.class)
	public void sprintValidateInBacklogId() {
		sprintValidations.sprintValidateInBacklog(TestUtils.getSprintTwo(), sprintsConstants.getSPRINT_ID());
	}

	@Test
	public void sprintValidateInBacklogWrong() {
		sprintValidations.sprintValidateInBacklog(null);
	}
	
	@Test
	public void sprintValidateInBacklogWrongId() {
		sprintValidations.sprintValidateInBacklog(null, sprintsConstants.getSPRINT_ID());
	}

	@Test
	public void sprintValidateInBacklogFalse() {
		sprintValidations.sprintValidateInBacklog(TestUtils.getSprint());
	}
	
	@Test
	public void sprintValidateInBacklogFalseId() {
		sprintValidations.sprintValidateInBacklog(TestUtils.getSprint(), sprintsConstants.getSPRINT_ID());
	}

	/***
	 * End date validations
	 */
	@Test(expected = EntityConflictException.class)
	public void testSprintsEndDateValidations() {
		sprintValidations.sprintsEndDateValidations(testUtils.getSprintDomainActive());
	}
	
	@Test(expected = EntityConflictException.class)
	public void testSprintsEndDateValidationsId() {
		sprintValidations.sprintsEndDateValidations(testUtils.getSprintDomainActive(), sprintsConstants.getSPRINT_ID());
	}

	@Test
	public void testSprintsEndDateValidationsFalse() {
		sprintValidations.sprintsEndDateValidations(testUtils.getSprintDomain());
	}
	
	@Test
	public void testSprintsEndDateValidationsFalseId() {
		sprintValidations.sprintsEndDateValidations(testUtils.getSprintDomain(), sprintsConstants.getSPRINT_ID());
	}

	/***
	 * This one test end date is on the future
	 */
	@Test
	public void testSprintsEndDateValidationsDate() {
		sprintValidations.sprintsEndDateValidations(testUtils.getSprintDomainEndDateFuture());
	}
	
	@Test
	public void testSprintsEndDateValidationsDateId() {
		sprintValidations.sprintsEndDateValidations(testUtils.getSprintDomainEndDateFuture(), sprintsConstants.getSPRINT_ID());
	}

	/***
	 * This one test end date is on the past
	 */
	@Test(expected = EntityConflictException.class)
	public void testSprintsEndDateValidationsDatePast() {
		sprintValidations.sprintsEndDateValidations(testUtils.getSprintDomainEndDatePast());
	}
	
	@Test(expected = EntityConflictException.class)
	public void testSprintsEndDateValidationsDatePastId() {
		sprintValidations.sprintsEndDateValidations(testUtils.getSprintDomainEndDatePast(), sprintsConstants.getSPRINT_ID());
	}

	/***
	 * Can not delete Active sprint
	 */
	@Test(expected = EntityConflictException.class)
	public void testSprintsActiveValidation() {
		sprintValidations.sprintsActiveValidation(testUtils.getOptionalSprint(), sprintsConstants.getSPRINT_ID());
	}

	@Test
	public void testSprintsActiveValidationFalse() {
		sprintValidations.sprintsActiveValidation(testUtils.getOptionalSprintActiveFalse(), sprintsConstants.getSPRINT_ID());
	}

	@Test(expected = EntityConflictException.class)
	public void sprintValidateStartDateAfter() {
		sprintValidations.sprintValidateStartDate(TestUtils.getDummySprintDomainStartDateAfter());
	}
	
	@Test(expected = EntityConflictException.class)
	public void sprintValidateStartDateAfterId() {
		sprintValidations.sprintValidateStartDate(TestUtils.getDummySprintDomainStartDateAfter(), sprintsConstants.getSPRINT_ID());
	}

	@Test(expected = EntityConflictException.class)
	public void sprintValidateStartDateEqual() {
		sprintValidations.sprintValidateStartDate(TestUtils.getDummySprintDomainStartDateEqual());
	}
	
	@Test(expected = EntityConflictException.class)
	public void sprintValidateStartDateEqualId() {
		sprintValidations.sprintValidateStartDate(TestUtils.getDummySprintDomainStartDateEqual(), sprintsConstants.getSPRINT_ID());
	}

	@Test
	public void sprintValidateStartDate() {
		sprintValidations.sprintValidateStartDate(TestUtils.getDummySprintDomainTrue());
	}
	@Test
	public void sprintValidateStartDateId() {
		sprintValidations.sprintValidateStartDate(TestUtils.getDummySprintDomainTrue(), sprintsConstants.getSPRINT_ID());
	}

	@Test(expected = BadRequestException.class)
	public void sprintsNameValidationsNull() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomainNameNull());
	}

	@Test(expected = BadRequestException.class)
	public void sprintsNameValidationsNullId() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomainNameNull(), sprintsConstants.getSPRINT_ID());
	}
	
	@Test(expected = BadRequestException.class)
	public void sprintsNameValidationsEmpty() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomainNameEmpty());
	}
	
	@Test(expected = BadRequestException.class)
	public void sprintsNameValidationsEmptyId() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomainNameEmpty(), sprintsConstants.getSPRINT_ID());
	}

	@Test(expected = BadRequestException.class)
	public void sprintsNameValidationsChars() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomainNull());
	}
	
	@Test(expected = BadRequestException.class)
	public void sprintsNameValidationsCharsId() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomainNull(), sprintsConstants.getSPRINT_ID());
	}

	@Test(expected = BadRequestException.class)
	public void sprintsNameValidationsNothing() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomainNoting());
	}
	
	@Test(expected = BadRequestException.class)
	public void sprintsNameValidationsNothingId() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomainNoting(), sprintsConstants.getSPRINT_ID());
	}

	@Test
	public void sprintsNameValidations() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomain());
	}
	
	@Test
	public void sprintsNameValidationsId() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomain(), sprintsConstants.getSPRINT_ID());
	}

}