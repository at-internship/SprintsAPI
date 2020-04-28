package com.sprints.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sprints.services.SprintsServiceImpl;
import com.sprints.utils.SprintsConstants;
import com.sprints.utils.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SprintsControllerTest {

	@InjectMocks
	private SprintsController sprintsController;

	@Mock
	private SprintsServiceImpl sprintsServiceImpl;

	private SprintsConstants sprintsConstants;

	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		sprintsConstants = new SprintsConstants();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testDeleteById() throws Exception {
		doNothing().when(sprintsServiceImpl).deleteById(anyString());
		sprintsController.deleteSprintById(sprintsConstants.getSprintId());
	}

	@Test
	public void testDeleteByIdNotFound() throws Exception {
		sprintsController.deleteSprintById(sprintsConstants.getSprintId());
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.delete(sprintsConstants.getDeleteUri(), sprintsConstants.getSprintId()))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(404, status);
	}

	@Test
	public void testFindSprintById() throws Exception {
		when(sprintsServiceImpl.findById(anyString())).thenReturn(TestUtils.getEmptySprintDomain());
		assertEquals(TestUtils.getEmptySprintDomain(),
				sprintsController.findSprintById(sprintsConstants.getSimpleId()));
	}

	@Test
	public void testFindSprintByIdNotFound() throws Exception {
		when(sprintsServiceImpl.findById(anyString())).thenReturn(TestUtils.getEmptySprintDomain());
		assertEquals(TestUtils.getEmptySprintDomain(),
				sprintsController.findSprintById(sprintsConstants.getSimpleId()));
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get(sprintsConstants.getGetByIdUri(), TestUtils.getDummySprintDomain().getId())
						.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(404, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertTrue(content.isEmpty());
	}

	@Test
	public void testFindAllSprints() throws Exception {
		when(sprintsServiceImpl.findAllSprints(sprintsConstants.getName(), sprintsConstants.getTechnology(),
				sprintsConstants.getStart_date(), sprintsConstants.getEnd_date()))
						.thenReturn(TestUtils.getEmptySprintDomainList());
		assertEquals(TestUtils.getEmptySprintDomainList(), sprintsController.findAllSprints(sprintsConstants.getName(),
				sprintsConstants.getTechnology(), sprintsConstants.getStart_date(), sprintsConstants.getEnd_date()));
	}

	@Test
	public void create() throws Exception {
		sprintsController.createSprint(TestUtils.getEmptySprintDomain());
		MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content("{{\n" + "\"name\": \"ValidationTesting\",\n" + "\"technology\": \"PEGA\",\n"
						+ "\"active\": true,\n" + "\"is_backlog\": false,\n" + "\"start_date\": \"2020-04-23\",\n"
						+ "\"end_date\": \"2020-05-25\"\n" + "}}");
		verify(sprintsServiceImpl, times(1)).createSprint(TestUtils.getEmptySprintDomain());
	}

	@Test
	public void testUpdateSprint() {
		when(sprintsServiceImpl.updateSprint(TestUtils.getEmptySprintDomain(), sprintsConstants.getSimpleId()))
				.thenReturn(TestUtils.getEmptySprintDomain());
		assertEquals(TestUtils.getEmptySprintDomain(),
				sprintsController.updateSprint(TestUtils.getEmptySprintDomain(), sprintsConstants.getSimpleId()));
	}
}
