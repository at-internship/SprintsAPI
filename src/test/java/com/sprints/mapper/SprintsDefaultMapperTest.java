package com.sprints.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sprints.utils.TestUtils;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SprintsDefaultMapperTest {
	
	@InjectMocks
	private SprintsDefaultMapper sprintsDefaultMapper;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
    public void testSprintDefaultValues() {
        assertEquals(TestUtils.getDummySprintDomainNull(),
                sprintsDefaultMapper.sprintsDefaultValues(TestUtils.getDummySprintDomainMapperValid()));
    }
   
    @Test
    public void testSprintDefaultValuesInvalid() {
        assertEquals(TestUtils.getDummySprintDomainMapperInvalid(),
                sprintsDefaultMapper.sprintsDefaultValues(TestUtils.getDummySprintDomainMapperInvalid()));
    }
}
