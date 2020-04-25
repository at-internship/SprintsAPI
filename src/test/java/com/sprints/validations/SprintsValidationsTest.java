package com.sprints.validations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sprints.exception.BadRequestException;
import com.sprints.exception.EntityConflictException;
import com.sprints.utils.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
public class SprintsValidationsTest {

	@InjectMocks
	private SprintsValidations sprintValidations;

	private TestUtils testUtils;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		testUtils = new TestUtils();
	}

	/***
	 * Sprint that validates Both Booleans
	 */
	@Test(expected = EntityConflictException.class)
	public void testSprintValidateBothBooleans() {
		sprintValidations.sprintValidateBothBooleans(testUtils.getSprintDomainTrue());
	}

	/***
	 * This one test active is true
	 */
	@Test
	public void testSprintValidateBothBooleansActive() {
		sprintValidations.sprintValidateBothBooleans(testUtils.getSprintDomainActive());
	}

	/***
	 * This one test backlog is true
	 */
	@Test
	public void testSprintValidateBothBooleansBacklog() {
		sprintValidations.sprintValidateBothBooleans(testUtils.getSprintDomainBacklog());
	}

	/***
	 * This one test active and backlog are false
	 */
	@Test
	public void testSprintValidateBothBooleansFalse() {
		sprintValidations.sprintValidateBothBooleans(testUtils.getSprintDomain());
	}

	/***
	 * Validation of active
	 */
	@Test(expected = EntityConflictException.class)
	public void testSprintsValidationsActive() {
		sprintValidations.sprintsValidationsActive(TestUtils.getSprint());
	}

	@Test
	public void testSprintsValidationsActiveWrong() {
		sprintValidations.sprintsValidationsActive(null);
	}

	@Test
	public void testSprintsValidationsActiveFalse() {
		sprintValidations.sprintsValidationsActive(TestUtils.getSprintTwo());
	}

	/***
	 * Validation of backlog
	 */
	@Test(expected = EntityConflictException.class)
	public void sprintValidateInBacklog() {
		sprintValidations.sprintValidateInBacklog(TestUtils.getSprintTwo());
	}

	@Test
	public void sprintValidateInBacklogWrong() {
		sprintValidations.sprintValidateInBacklog(null);
	}

	@Test
	public void sprintValidateInBacklogFalse() {
		sprintValidations.sprintValidateInBacklog(TestUtils.getSprint());
	}

	/***
	 * End date validations
	 */
	@Test(expected = EntityConflictException.class)
	public void testSprintsEndDateValidations() {
		sprintValidations.sprintsEndDateValidations(testUtils.getSprintDomainActive());
	}

	@Test
	public void testSprintsEndDateValidationsFalse() {
		sprintValidations.sprintsEndDateValidations(testUtils.getSprintDomain());
	}

	/***
	 * This one test end date is on the future
	 */
	@Test
	public void testSprintsEndDateValidationsDate() {
		sprintValidations.sprintsEndDateValidations(testUtils.getSprintDomainEndDateFuture());
	}

	/***
	 * This one test end date is on the past
	 */
	@Test(expected = EntityConflictException.class)
	public void testSprintsEndDateValidationsDatePast() {
		sprintValidations.sprintsEndDateValidations(testUtils.getSprintDomainEndDatePast());
	}

	/***
	 * Can not delete Active sprint
	 */
	@Test(expected = EntityConflictException.class)
	public void testSprintsActiveValidation() {
		sprintValidations.sprintsActiveValidation(testUtils.getOptionalSprint());
	}

	@Test
	public void testSprintsActiveValidationFalse() {
		sprintValidations.sprintsActiveValidation(testUtils.getOptionalSprintActiveFalse());
	}

	@Test(expected = EntityConflictException.class)
	public void sprintValidateStartDateAfter() {
		sprintValidations.sprintValidateStartDate(TestUtils.getDummySprintDomainStartDateAfter());
	}

	@Test(expected = EntityConflictException.class)
	public void sprintValidateStartDateEqual() {
		sprintValidations.sprintValidateStartDate(TestUtils.getDummySprintDomainStartDateEqual());
	}

	@Test
	public void sprintValidateStartDate() {
		sprintValidations.sprintValidateStartDate(TestUtils.getDummySprintDomainTrue());
	}

	@Test(expected = BadRequestException.class)
	public void sprintsNameValidationsNull() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomainNameNull());
	}

	@Test(expected = BadRequestException.class)
	public void sprintsNameValidationsEmpty() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomainNameEmpty());
	}

	@Test(expected = BadRequestException.class)
	public void sprintsNameValidationsChars() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomainNull());
	}

	@Test(expected = BadRequestException.class)
	public void sprintsNameValidationsNothing() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomainNoting());
	}

	@Test
	public void sprintsNameValidations() {
		sprintValidations.sprintsNameValidations(TestUtils.getDummySprintDomain());
	}

}