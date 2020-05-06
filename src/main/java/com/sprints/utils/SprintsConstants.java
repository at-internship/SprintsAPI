package com.sprints.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.mongodb.core.query.Query;

import lombok.Getter;

@Getter
public class SprintsConstants {

	final String GET_URI = "/";
	final String GET_BY_ID_URI = "/{id}";
	final String DELETE_URI = "/{id}";
	final String POST_URI = "/";
	final String PUT_URI = "/{id}";
	final String SPRINT_ID = "5e837d8948b0866f87e1cc7f";
	final String SIMPLE_ID = "5e7668cfacfc726352dc5abc";
	final String ID = "1231231441";
	final String STRING_NAME = "Sprints-API";
	final String CREATE_TEST_CONTENT = "{{\n" + "\"name\": \"ValidationTesting\",\n" + "\"technology\": \"PEGA\",\n"
			+ "\"active\": true,\n" + "\"is_backlog\": false,\n" + "\"start_date\": \"2020-04-23\",\n"
			+ "\"end_date\": \"2020-05-25\"\n" + "}}";
	final String SNANME = "name";
	final String STRING_TECHNOLOGY = "technology";
	final String STRING_START_DATE = "start_date";
	final String STRING_END_DATE = "end_date";
	final String STRING_ACTIVE = "active";
	final String STRING_IS_BACKLOG = "is_backlog";

	final boolean BOOLEAN_TRUE = true;
	final boolean BOOLEAN_FALSE = false;

	final Optional<String> CRITERIA_NAME = Optional.of("Sprint-Dummy");
	final Optional<String> CRITERIA_TECHNOLOGY = Optional.of("Postman");
	final Optional<LocalDate> CRITERIA_START_DATE = Optional.of(LocalDate.now());
	final Optional<LocalDate> CRITERIA_END_DATE = Optional.of(LocalDate.now());

	final Optional<String> CRITERIA_START_D = Optional.of(LocalDate.now().toString());
	final Optional<String> CRITERIA_END_D = Optional.of(LocalDate.now().toString());

	final Optional<String> CRITERIA_START_D_EMPTY = Optional.empty();
	final Optional<String> CRITERIA_END_D_EMPTY = Optional.empty();

	final Optional<String> CRITERIA_NAME_EMPTY = Optional.empty();
	final Optional<String> CRITERIA_TECHNOLOGY_EMPTY = Optional.empty();;
	final Optional<LocalDate> CRITERIA_START_DATE_EMPTY = Optional.empty();
	final Optional<LocalDate> CRITERIA_END_DATE_EMPTY = Optional.empty();

	final Optional<String> CRITERIA_NAME_BLANK = Optional.of(" ");
	final Optional<String> CRITERIA_TECHNOLOGY_BLANK = Optional.of(" ");
	final Optional<String> CRITERIA_START_DATE_BLANK = Optional.of(" ");
	final Optional<String> CRITERIA_END_DATE_BLANK = Optional.of(" ");

	final Optional<String> CRITERIA_NO_START_DATE = Optional.of("aversijala");
	final Optional<String> CRITERIA_NO_END_DATE = Optional.of("aversijala");

	final LocalDate LOCAL_DATE = CRITERIA_START_DATE.get();
	final Date DATE = Date.from(LOCAL_DATE.atStartOfDay(ZoneId.of("UTC")).toInstant());

	Optional<String> NAME = Optional.empty();
	Optional<String> TECHNOLOGY = Optional.empty();
	Optional<String> START_DATE = Optional.empty();
	Optional<String> END_DATE = Optional.empty();

	final Criteria CRITERIA_EMPTY = new Criteria();
	final Criteria CRITERIA = new Criteria().and(STRING_NAME).is(CRITERIA_NAME.get()).and(STRING_TECHNOLOGY)
			.is(CRITERIA_TECHNOLOGY.get()).and(STRING_START_DATE).is(DATE).and(STRING_END_DATE).is(DATE);
	final Criteria CRITERIA_1 = new Criteria().and(SNANME).is(CRITERIA_NAME.get()).and(STRING_TECHNOLOGY)
			.is(CRITERIA_TECHNOLOGY.get()).and(STRING_START_DATE).is(DATE).and(STRING_END_DATE).is(DATE);
	final Criteria CRITERIA_NO_DATES = new Criteria().and(SNANME).is(CRITERIA_NAME.get()).and(STRING_TECHNOLOGY)
			.is(CRITERIA_TECHNOLOGY.get());
	

}
