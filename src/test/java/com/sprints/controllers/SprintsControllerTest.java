package com.sprints.controllers;

import static org.mockito.Mockito.doNothing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sprints.services.SprintsServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class SprintsControllerTest {

	@InjectMocks
	private SprintsController sprintsController;

	@Mock
	private SprintsServiceImpl sprintsServiceImpl;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testDeleteById() {
		String sprintID = "dsadasd7sadb";

		log.info("--------Test del método delete-------");
		doNothing().when(sprintsServiceImpl).deleteById(sprintID);
		// void responseTest = sprintsController.deleteSprintById(SprintID);
		// assertEquals(HttpStatus.NO_CONTENT, responseTest.getStatusCode());
	}

}
