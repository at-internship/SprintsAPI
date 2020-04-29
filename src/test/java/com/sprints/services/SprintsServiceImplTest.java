package com.sprints.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sprints.domain.SprintDomain;
import com.sprints.exception.EntityNotFoundException;
import com.sprints.mapper.SprintsDefaultMapper;
import com.sprints.mapper.SprintsTransformer;
import com.sprints.model.Sprint;
import com.sprints.repository.SprintsCustomRepository;
import com.sprints.repository.SprintsCustomRepositoryImpl;
import com.sprints.repository.SprintsRepository;
import com.sprints.utils.SprintsConstants;
import com.sprints.utils.TestUtils;
import com.sprints.validations.SprintsValidations;
import com.sprints.validations.ValidateQueryParams;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SprintsServiceImplTest {

	@InjectMocks
	private SprintsServiceImpl sprintsServiceImpl;

	private TestUtils testUtils;
	private SprintsConstants sprintsConstants;

	@Mock
	private SprintsRepository sprintsRepository;

	@Mock
	private SprintsCustomRepository sprintsValidationsRepository;

	@Mock
	private SprintsCustomRepositoryImpl sprintsValidationsRepositoryImpl;

	@Mock
	private SprintsTransformer sprintsTransformer;

	@Mock
	private SprintsValidations sprintsValidations;

	@Mock
	private SprintsDefaultMapper sprintDefault;

	@Mock
	private ValidateQueryParams validateQueryParams;

	@Mock
	private SprintsDefaultMapper sprintsDefaultMapper;

	@Mock
	private Sprint sprint;

	@Mock
	private SprintDomain sprintDomain;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		sprintsConstants = new SprintsConstants();
		testUtils = new TestUtils();
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testFindById() {

		when(sprintsRepository.existsById(sprintsConstants.getSprintId())).thenReturn(sprintsConstants.isBooleanTrue());
		when(sprintsRepository.findById(sprintsConstants.getSprintId()))
				.thenReturn(Optional.of(TestUtils.getDummySprint()));
		when(sprintsTransformer.transformer(TestUtils.getDummySprint())).thenReturn(TestUtils.getDummySprintDomain());
		Assert.assertEquals("SprintDomain-Dummy",
				sprintsServiceImpl.findById(sprintsConstants.getSprintId()).getName());
	}

	@Test(expected = com.sprints.exception.EntityNotFoundException.class)
	public void testFindByIdFailed() {

		when(sprintsRepository.existsById(anyString())).thenReturn(sprintsConstants.isBooleanFalse());
		sprintsServiceImpl.findById(sprintsConstants.getId());
	}

	@Test
	public void testDeleteById() {

		doNothing().when(sprintsRepository).deleteById(anyString());
		doReturn(true).when(sprintsRepository).existsById(anyString());
		sprintsServiceImpl.deleteById(sprintsConstants.getSprintId());
		verify(sprintsRepository, times(1)).deleteById(sprintsConstants.getSprintId());
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteById_EntityNotFoundException() {

		doNothing().when(sprintsRepository).deleteById(anyString());
		doReturn(sprintsConstants.isBooleanFalse()).when(sprintsRepository).existsById(sprintsConstants.getSprintId());
		sprintsServiceImpl.deleteById(sprintsConstants.getSprintId());
	}

	@Test
	public void findAll() {

		when(sprintsRepository.findAll()).thenReturn(TestUtils.getDummySprintList());
		when(sprintsTransformer.listTransformer(TestUtils.getDummySprintList()))
				.thenReturn(TestUtils.getDummySprintDomainList());
		assertEquals(1, sprintsServiceImpl.findAll().size());

	}

	@Test
	public void createSprint() {

		when(sprintDefault.sprintsDefaultValues(TestUtils.getDummySprintDomain()))
				.thenReturn(TestUtils.getDummySprintDomain());
		doNothing().when(sprintsValidations).sprintValidateBothBooleans(TestUtils.getDummySprintDomain());
		doNothing().when(sprintsValidations).sprintsNameValidations(TestUtils.getDummySprintDomain());
		when(sprintsValidationsRepositoryImpl.oneSprintActiveValidation()).thenReturn(TestUtils.getDummySprint());
		doNothing().when(sprintsValidations).sprintsValidationsActive(TestUtils.getDummySprint());
		when(sprintsValidationsRepositoryImpl.oneSprintBacklogValidation()).thenReturn(TestUtils.getDummySprint());
		doNothing().when(sprintsValidations).sprintValidateInBacklog(TestUtils.getDummySprint());
		doNothing().when(sprintsValidations).sprintValidateStartDate(TestUtils.getDummySprintDomain());
		doNothing().when(sprintsValidations).sprintsEndDateValidations(TestUtils.getDummySprintDomain());
		when(sprintsTransformer.transformer(TestUtils.getDummySprintDomain())).thenReturn(TestUtils.getDummySprint());
		when(sprintsRepository.save(TestUtils.getDummySprint())).thenReturn(TestUtils.getDummySprint());
		sprintsServiceImpl.createSprint(TestUtils.getDummySprintDomain());
		assertEquals(TestUtils.getDummySprint().getId(),
				sprintsServiceImpl.createSprint(TestUtils.getDummySprintDomain()));
	}

	@Test
	public void createSprintNoIf() {

		when(sprintDefault.sprintsDefaultValues(TestUtils.getDummySprintDomainFalse()))
				.thenReturn(TestUtils.getDummySprintDomainFalse());
		doNothing().when(sprintsValidations).sprintValidateBothBooleans(TestUtils.getDummySprintDomainFalse());
		doNothing().when(sprintsValidations).sprintsNameValidations(TestUtils.getDummySprintDomain());
		when(sprintsTransformer.transformer(TestUtils.getDummySprintDomainFalse()))
				.thenReturn(TestUtils.getDummySprint());
		when(sprintsRepository.save(TestUtils.getDummySprint())).thenReturn(TestUtils.getDummySprint());
		sprintsServiceImpl.createSprint(TestUtils.getDummySprintDomainFalse());
		assertEquals(TestUtils.getDummySprint().getId(),
				sprintsServiceImpl.createSprint(TestUtils.getDummySprintDomainFalse()));
	}

	@Test
	public void updateSprint() {

		when(sprintsRepository.existsById(anyString())).thenReturn(sprintsConstants.isBooleanTrue());
		when(sprintDefault.sprintsDefaultValues(TestUtils.getDummySprintDomain()))
				.thenReturn(TestUtils.getDummySprintDomain());
		doNothing().when(sprintsValidations).sprintValidateBothBooleans(TestUtils.getDummySprintDomain());
		doNothing().when(sprintsValidations).sprintsNameValidations(TestUtils.getDummySprintDomain());
		when(sprintsValidationsRepositoryImpl.oneSprintActiveValidation()).thenReturn(TestUtils.getDummySprint());
		doNothing().when(sprintsValidations).sprintsValidationsActive(TestUtils.getDummySprint());
		when(sprintsValidationsRepositoryImpl.oneSprintBacklogValidation()).thenReturn(TestUtils.getDummySprint());
		doNothing().when(sprintsValidations).sprintValidateInBacklog(TestUtils.getDummySprint());
		doNothing().when(sprintsValidations).sprintValidateStartDate(TestUtils.getDummySprintDomain());
		doNothing().when(sprintsValidations).sprintsEndDateValidations(TestUtils.getDummySprintDomain());
		when(sprintsTransformer.transformer(TestUtils.getDummySprintDomain())).thenReturn(TestUtils.getDummySprint());
		assertEquals(TestUtils.getDummySprintDomain(),
				sprintsServiceImpl.updateSprint(TestUtils.getDummySprintDomain(), sprintsConstants.getSprintId()));
	}

	@Test
	public void updateSprintNoIf() {

		when(sprintsRepository.existsById(anyString())).thenReturn(sprintsConstants.isBooleanTrue());
		when(sprintDefault.sprintsDefaultValues(TestUtils.getDummySprintDomainFalse()))
				.thenReturn(TestUtils.getDummySprintDomainFalse());
		doNothing().when(sprintsValidations).sprintValidateBothBooleans(TestUtils.getDummySprintDomainFalse());
		doNothing().when(sprintsValidations).sprintsNameValidations(TestUtils.getDummySprintDomain());
		when(sprintsTransformer.transformer(TestUtils.getDummySprintDomainFalse()))
				.thenReturn(TestUtils.getDummySprint());
		assertEquals(TestUtils.getDummySprintDomainFalse(),
				sprintsServiceImpl.updateSprint(TestUtils.getDummySprintDomainFalse(), sprintsConstants.getSprintId()));
	}

	@Test(expected = EntityNotFoundException.class)
	public void updateSprintNotFound() {
		when(sprintsRepository.existsById(anyString())).thenReturn(sprintsConstants.isBooleanFalse());
		sprintsServiceImpl.updateSprint(TestUtils.getDummySprintDomain(), sprintsConstants.getSprintId());
	}

	@Test
	public void findAllByParams() {

		when(validateQueryParams.fillCriteriaWithParams(sprintsConstants.getName(), sprintsConstants.getTechnology(),
				sprintsConstants.getStart_date(), sprintsConstants.getEnd_date()))
						.thenReturn(TestUtils.getEmptyCriteria());
		when(sprintsValidationsRepository.findAllByParams(TestUtils.getEmptyCriteria()))
				.thenReturn(TestUtils.getEmptySprintList());
		when(sprintsTransformer.listTransformer(TestUtils.getEmptySprintList()))
				.thenReturn(TestUtils.getEmptySprintDomainList());
		sprintsServiceImpl.findAllByParams(sprintsConstants.getName(), sprintsConstants.getTechnology(),
				sprintsConstants.getStart_date(), sprintsConstants.getEnd_date());
		assertEquals(TestUtils.getEmptySprintDomainList(),
				sprintsServiceImpl.findAllByParams(sprintsConstants.getName(), sprintsConstants.getTechnology(),
						sprintsConstants.getStart_date(), sprintsConstants.getEnd_date()));

	}

	@Test
	public void testFindAllSprints() {
		assertEquals(testUtils.EmptySprintDomainList(),
				sprintsServiceImpl.findAllSprints(sprintsConstants.getCriteriaNameEmpty(),
						sprintsConstants.getCriteriaTechnology(), sprintsConstants.getCriteriaStartD(),
						sprintsConstants.getCriteriaEndD()));
	}

	@Test
	public void testFindAllSprintIf() {
		assertEquals(testUtils.EmptySprintDomainList(),
				sprintsServiceImpl.findAllSprints(sprintsConstants.getCriteriaNameEmpty(),
						sprintsConstants.getCriteriaTechnologyEmpty(), sprintsConstants.getCriteriaStartDEmpty(),
						sprintsConstants.getCriteriaEndDEmpty()));
	}
}
