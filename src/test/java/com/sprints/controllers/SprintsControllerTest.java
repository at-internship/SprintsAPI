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
		sprintsController.deleteSprintById(sprintsConstants.getSPRINT_ID());
	}

	@Test
	public void testDeleteByIdNotFound() throws Exception {
		sprintsController.deleteSprintById(sprintsConstants.getSPRINT_ID());
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.delete(sprintsConstants.getDELETE_URI(), sprintsConstants.getSPRINT_ID()))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(404, status);
	}

	@Test
	public void testFindSprintById() throws Exception {
		when(sprintsServiceImpl.findById(anyString())).thenReturn(TestUtils.getEmptySprintDomain());
		assertEquals(TestUtils.getEmptySprintDomain(),
				sprintsController.findSprintById(sprintsConstants.getSIMPLE_ID()));
	}

	@Test
	public void testFindSprintByIdNotFound() throws Exception {
		when(sprintsServiceImpl.findById(anyString())).thenReturn(TestUtils.getEmptySprintDomain());
		assertEquals(TestUtils.getEmptySprintDomain(),
				sprintsController.findSprintById(sprintsConstants.getSIMPLE_ID()));
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get(sprintsConstants.getGET_BY_ID_URI(), TestUtils.getDummySprintDomain().getId())
						.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(404, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertTrue(content.isEmpty());
	}

	@Test
	public void testFindAllSprints() throws Exception {
		when(sprintsServiceImpl.findAllSprints(sprintsConstants.getNAME(), sprintsConstants.getTECHNOLOGY(),
				sprintsConstants.getSTART_DATE(), sprintsConstants.getEND_DATE()))
						.thenReturn(TestUtils.getEmptySprintDomainList());
		assertEquals(TestUtils.getEmptySprintDomainList(), sprintsController.findAllSprints(sprintsConstants.getNAME(),
				sprintsConstants.getTECHNOLOGY(), sprintsConstants.getSTART_DATE(), sprintsConstants.getEND_DATE()));
	}

	@Test
	public void create() throws Exception {
		sprintsController.createSprint(TestUtils.getEmptySprintDomain());
		MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(sprintsConstants.getCREATE_TEST_CONTENT());
		verify(sprintsServiceImpl, times(1)).createSprint(TestUtils.getEmptySprintDomain());
	}

	@Test
	public void testUpdateSprint() {
		when(sprintsServiceImpl.updateSprint(TestUtils.getEmptySprintDomain(), sprintsConstants.getSIMPLE_ID()))
				.thenReturn(TestUtils.getEmptySprintDomain());
		assertEquals(TestUtils.getEmptySprintDomain(),
				sprintsController.updateSprint(TestUtils.getEmptySprintDomain(), sprintsConstants.getSIMPLE_ID()));
	}
}
