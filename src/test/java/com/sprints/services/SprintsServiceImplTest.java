package com.sprints.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprints.repository.SprintsRepository;


@SpringBootTest
class SprintsServiceImplTest {

	@Mock
	private SprintsRepository sprintsRepository;
	
	@InjectMocks
	private SprintsService sprintsService = new SprintsServiceImpl();
	
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
}
