package com.sprints.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprints.domain.SprintDomain;
import com.sprints.model.Sprint;

@Component
public class TestUtils {

	public TestUtils() {
	}
	
	public static Criteria getEmptyCriteria() {
		Criteria getEmptyCriteria = new Criteria();
		return getEmptyCriteria;
	}

	public Sprint getEmptySprint() {
		Sprint sprint = new Sprint();
		return sprint;
	}

	public static SprintDomain getEmptySprintDomain() {
		SprintDomain sprintDomain = new SprintDomain();
		return sprintDomain;
	}

	public static List<Sprint> getEmptySprintList() {
		List<Sprint> sprintList = new ArrayList<Sprint>();
		return sprintList;
	}

	public static List<SprintDomain> getEmptySprintDomainList() {
		List<SprintDomain> sprintDomainList = new ArrayList<SprintDomain>();
		return sprintDomainList;
	}
	
	public List<SprintDomain> EmptySprintDomainList() {
		List<SprintDomain> sprintDomainList = new ArrayList<SprintDomain>();
		return sprintDomainList;
	}

	public static Sprint getDummySprintTrue() {
		LocalDate start_date = LocalDate.now();
		LocalDate end_date = start_date.plusDays(10);
		Sprint sprint = new Sprint();
		sprint.setName("sprint de prueba");
		sprint.setTechnology("java");
		sprint.setActive(true);
		sprint.setIs_backlog(true);
		sprint.setStart_date(start_date);
		sprint.setEnd_date(end_date);
		return sprint;
	}

	public static SprintDomain getDummySprintDomainStartDateAfter() {
		LocalDate start_date = LocalDate.parse("2020-04-30");
		LocalDate end_date = LocalDate.parse("2020-04-24");
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e7668cfacfc726352dc5abc");
		sprintDomain.setName("Sprint-Dummy");
		sprintDomain.setTechnology("Java");
		sprintDomain.setActive(true);
		sprintDomain.setIs_backlog(false);
		sprintDomain.setStart_date(start_date);
		sprintDomain.setEnd_date(end_date);
		return sprintDomain;
	}

	public static SprintDomain getDummySprintDomainStartDateEqual() {
		LocalDate start_date = LocalDate.parse("2020-04-30");
		LocalDate end_date = LocalDate.parse("2020-04-30");
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e7668cfacfc726352dc5abc");
		sprintDomain.setName("Sprint-Dummy");
		sprintDomain.setTechnology("Java");
		sprintDomain.setActive(true);
		sprintDomain.setIs_backlog(false);
		sprintDomain.setStart_date(start_date);
		sprintDomain.setEnd_date(end_date);
		return sprintDomain;
	}

	public static Sprint getDummySprint() {
		LocalDate start_date = LocalDate.now();
		LocalDate end_date = start_date.plusDays(10);

		Sprint sprint = new Sprint();
		sprint.setId("5e7668cfacfc726352dc5abc");
		sprint.setName("Sprint-Dummy");
		sprint.setTechnology("Java");
		sprint.setActive(false);
		sprint.setIs_backlog(false);
		sprint.setStart_date(start_date);
		sprint.setEnd_date(end_date);
		return sprint;
	}
	
	public static Sprint getDummySprintFalse() {
		LocalDate start_date = LocalDate.now();

		Sprint sprint = new Sprint();
		sprint.setId("5e7668cfacfc726352dc5abc");
		sprint.setName("Sprint-Dummy");
		sprint.setTechnology("Java");
		sprint.setActive(false);
		sprint.setIs_backlog(false);
		sprint.setStart_date(start_date);
		sprint.setEnd_date(null);
		return sprint;
	}

	public static SprintDomain getDummySprintDomainTrue() {
		LocalDate start_date = LocalDate.now();
		LocalDate end_date = start_date.plusDays(10);
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e7668cfacfc726352dc5abc");
		sprintDomain.setName("sprint de prueba");
		sprintDomain.setTechnology("javasconloeso");
		sprintDomain.setActive(true);
		sprintDomain.setIs_backlog(true);
		sprintDomain.setStart_date(start_date);
		sprintDomain.setEnd_date(end_date);
		return sprintDomain;
	}

	public static SprintDomain getDummySprintDomain() {
		LocalDate start_date = LocalDate.now();
		LocalDate end_date = start_date.plusDays(10);

		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e837d8948b0866f87e1cc7f");
		sprintDomain.setName("SprintDomain-Dummy");
		sprintDomain.setTechnology("Java");
		sprintDomain.setActive(true);
		sprintDomain.setIs_backlog(true);
		sprintDomain.setStart_date(start_date);
		sprintDomain.setEnd_date(end_date);
		return sprintDomain;
	}

	public static SprintDomain getDummySprintDomainNoting() {
		LocalDate start_date = LocalDate.now();
		LocalDate end_date = start_date.plusDays(10);
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e7668cfacfc726352dc5abc");
		sprintDomain.setName("");
		sprintDomain.setTechnology("Java");
		sprintDomain.setActive(false);
		sprintDomain.setIs_backlog(true);
		sprintDomain.setStart_date(start_date);
		sprintDomain.setEnd_date(end_date);
		return sprintDomain;
	}

	public static SprintDomain getDummySprintDomainNameNull() {
		LocalDate start_date = LocalDate.now();
		LocalDate end_date = start_date.plusDays(10);
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e7668cfacfc726352dc5abc");
		sprintDomain.setName(null);
		sprintDomain.setTechnology("Java");
		sprintDomain.setActive(false);
		sprintDomain.setIs_backlog(true);
		sprintDomain.setStart_date(start_date);
		sprintDomain.setEnd_date(end_date);
		return sprintDomain;
	}

	public static SprintDomain getDummySprintDomainNameEmpty() {
		LocalDate start_date = LocalDate.now();
		LocalDate end_date = start_date.plusDays(10);
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e7668cfacfc726352dc5abc");
		sprintDomain.setName(" ");
		sprintDomain.setTechnology("Java");
		sprintDomain.setActive(false);
		sprintDomain.setIs_backlog(true);
		sprintDomain.setStart_date(start_date);
		sprintDomain.setEnd_date(end_date);
		return sprintDomain;
	}

	public static SprintDomain getDummySprintDomainNull() {
		LocalDate start_date = LocalDate.now();
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setActive(false);
		sprintDomain.setIs_backlog(false);
		sprintDomain.setStart_date(start_date);
		sprintDomain.setEnd_date(null);
		return sprintDomain;
	}
	
	public static SprintDomain getDummySprintDomainFalse() {
		LocalDate start_date = LocalDate.now();
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e837d8948b0866f87e1cc7f");
		sprintDomain.setName("SprintDomain-Dummy");
		sprintDomain.setTechnology("Java");
		sprintDomain.setActive(false);
		sprintDomain.setIs_backlog(false);
		sprintDomain.setStart_date(start_date);
		sprintDomain.setEnd_date(null);
		return sprintDomain;
	}

	public static SprintDomain getDummySprintDomainMapperValid() {
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e7668cfacfc726352dc5abc");
		sprintDomain.setActive(null);
		sprintDomain.setIs_backlog(null);
		sprintDomain.setStart_date(null);
		return sprintDomain;
	}

	public static SprintDomain getDummySprintDomainMapperInvalid() {
		LocalDate start_date = LocalDate.now();
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId(null);
		sprintDomain.setActive(true);
		sprintDomain.setIs_backlog(true);
		sprintDomain.setStart_date(start_date);
		return sprintDomain;
	}

	public static List<Sprint> getDummySprintList() {
		LocalDate start_date = LocalDate.now();
		LocalDate end_date = start_date.plusDays(10);
		Sprint sprint = new Sprint();
		sprint.setName("sprint de prueba");
		sprint.setTechnology("javasconloeso");
		sprint.setActive(true);
		sprint.setIs_backlog(true);
		sprint.setStart_date(start_date);
		sprint.setEnd_date(end_date);
		List<Sprint> sprintList = new ArrayList<Sprint>();
		sprintList.add(sprint);
		return sprintList;
	}

	public static List<SprintDomain> getDummySprintDomainList() {
		LocalDate start_date = LocalDate.now();
		LocalDate end_date = start_date.plusDays(10);
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setName("sprint de prueba");
		sprintDomain.setTechnology("javasconloeso");
		sprintDomain.setActive(true);
		sprintDomain.setIs_backlog(true);
		sprintDomain.setStart_date(start_date);
		sprintDomain.setEnd_date(end_date);
		List<SprintDomain> sprintDomainList = new ArrayList<SprintDomain>();
		sprintDomainList.add(sprintDomain);
		return sprintDomainList;
	}

	public static Sprint getSprint() {
		Sprint sprint = new Sprint();
		sprint.setId("5e7668cfacfc726352dc5abc");
		sprint.setName("Sprint-Dummy");
		sprint.setTechnology("Java");
		sprint.setActive(true);
		sprint.setIs_backlog(false);
		sprint.setStart_date(LocalDate.now());
		sprint.setEnd_date(LocalDate.now());
		return sprint;
	}

	public static Sprint getSprintTwo() {
		Sprint sprint = new Sprint();
		sprint.setId("6e7668cfacfc726352dc5abc");
		sprint.setName("Sprint-Dummy-Two");
		sprint.setTechnology("Postman");
		sprint.setActive(false);
		sprint.setIs_backlog(true);
		sprint.setStart_date(LocalDate.now());
		sprint.setEnd_date(LocalDate.now());
		return sprint;
	}

	public Sprint getSprintTrue() {
		Sprint sprint = new Sprint();
		sprint.setId("5e7668cfacfc726352dc5abc");
		sprint.setName("Sprint-Dummy");
		sprint.setTechnology("Java");
		sprint.setActive(true);
		sprint.setIs_backlog(true);
		sprint.setStart_date(LocalDate.now());
		sprint.setEnd_date(LocalDate.now());
		return sprint;
	}

	public SprintDomain getSprintDomain() {
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e7668cfacfc726352dc5abc");
		sprintDomain.setName("Sprint-Dummy");
		sprintDomain.setTechnology("Java");
		sprintDomain.setActive(false);
		sprintDomain.setIs_backlog(false);
		sprintDomain.setStart_date(LocalDate.now());
		sprintDomain.setEnd_date(LocalDate.now());
		return sprintDomain;
	}

	public SprintDomain getSprintDomainActive() {
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e7668cfacfc726352dc5abc");
		sprintDomain.setName("Sprint-Dummy");
		sprintDomain.setTechnology("Java");
		sprintDomain.setActive(true);
		sprintDomain.setIs_backlog(false);
		sprintDomain.setStart_date(LocalDate.now());
		sprintDomain.setEnd_date(LocalDate.now());
		return sprintDomain;
	}

	public SprintDomain getSprintDomainBacklog() {
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e7668cfacfc726352dc5abc");
		sprintDomain.setActive(false);
		sprintDomain.setIs_backlog(true);
		return sprintDomain;
	}

	public SprintDomain getSprintDomainTrue() {
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e7668cfacfc726352dc5abc");
		sprintDomain.setName("Sprint-Dummy");
		sprintDomain.setTechnology("Java");
		sprintDomain.setActive(true);
		sprintDomain.setIs_backlog(true);
		sprintDomain.setStart_date(LocalDate.now());
		sprintDomain.setEnd_date(LocalDate.now());
		return sprintDomain;
	}

	public SprintDomain getSprintDomainEndDateFuture() {
		LocalDate start_date = LocalDate.now();
		LocalDate end_date = start_date.plusDays(10);
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e7668cfacfc726352dc5abc");
		sprintDomain.setActive(true);
		sprintDomain.setEnd_date(end_date);
		return sprintDomain;
	}

	public SprintDomain getSprintDomainEndDatePast() {
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e7668cfacfc726352dc5abc");
		sprintDomain.setActive(true);
		sprintDomain.setStart_date(LocalDate.now());
		sprintDomain.setEnd_date(LocalDate.of(2020, 04, 1));
		return sprintDomain;
	}

	public SprintDomain getSprintDomainNull() {
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setActive(false);
		sprintDomain.setIs_backlog(false);
		sprintDomain.setStart_date(LocalDate.now());
		return sprintDomain;
	}

	public SprintDomain getSprintDomainMapperValid() {
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId("5e7668cfacfc726352dc5abc");
		sprintDomain.setActive(null);
		sprintDomain.setIs_backlog(null);
		sprintDomain.setStart_date(null);
		return sprintDomain;
	}

	public SprintDomain getSprintDomainMapperInvalid() {
		SprintDomain sprintDomain = new SprintDomain();
		sprintDomain.setId(null);
		sprintDomain.setActive(true);
		sprintDomain.setIs_backlog(true);
		sprintDomain.setStart_date(LocalDate.now());
		return sprintDomain;
	}

	public static List<Sprint> getSomeSprintsList() {
		List<Sprint> sprintList = new ArrayList<Sprint>();
		sprintList.add(getSprint());
		sprintList.add(getSprintTwo());
		return sprintList;
	}

	public Optional<Sprint> getOptionalSprint() {
		Sprint sprint = new Sprint();
		sprint.setId("5e7668cfacfc726352dc5abc");
		sprint.setName("Sprint-Dummy");
		sprint.setTechnology("Java");
		sprint.setActive(true);
		sprint.setIs_backlog(true);
		sprint.setStart_date(LocalDate.now());
		sprint.setEnd_date(LocalDate.now());
		Optional<Sprint> sprintOptional = Optional.of(sprint);
		return sprintOptional;
	}

	public Optional<Sprint> getOptionalSprintActiveFalse() {
		Sprint sprint = new Sprint();
		sprint.setId("5e7668cfacfc726352dc5abc");
		sprint.setName("Sprint-Dummy");
		sprint.setTechnology("Java");
		sprint.setActive(false);
		sprint.setIs_backlog(true);
		sprint.setStart_date(LocalDate.now());
		sprint.setEnd_date(LocalDate.now());
		Optional<Sprint> sprintOptional = Optional.of(sprint);
		return sprintOptional;
	}

	public static String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
}
