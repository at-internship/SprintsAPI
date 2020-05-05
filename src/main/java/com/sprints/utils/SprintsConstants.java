package com.sprints.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class SprintsConstants {

	final String getUri = "/";
	final String getByIdUri = "/{id}";
	final String deleteUri = "/{id}";
	final String postUri = "/";
	final String putUri = "/{id}";
	final String SprintId = "5e837d8948b0866f87e1cc7f";
	final String simpleId = "5e7668cfacfc726352dc5abc";
	final String id = "1231231441";
	final String stringName = "Sprints-API";
	final String sName = "name";
	final String stringTechnology = "technology";
	final String stringStartDate = "start_date";
	final String stringEndDate = "end_date";

	public static final String getByIdDescription = "Find a Sprint by id";
	public static final String getAllDescription = "Finds all Sprints";
	public static final String deleteDescription = "Delete a Sprint by id";
	public static final String postDescription = "Add new Sprint";
	public static final String putDescription = "Update an existing sprint by id";
	public static final String message200 = "OK";
	public static final String message201 = "Created";
	public static final String message204 = "No Content";
	public static final String message400 = "The JSON format provided is invalid";
	public static final String message404 = "Sprint Not Found";
	public static final String message409 = "Conflict";
	public static final String message409Delete = "You cannot delete an active Sprint\"";

	final boolean booleanTrue = true;
	final boolean booleanFalse = false;

	final Optional<String> criteriaName = Optional.of("Sprint-Dummy");
	final Optional<String> criteriaTechnology = Optional.of("Postman");
	final Optional<LocalDate> criteriaStartDate = Optional.of(LocalDate.now());
	final Optional<LocalDate> criteriaEndDate = Optional.of(LocalDate.now());

	final Optional<String> criteriaStartD = Optional.of(LocalDate.now().toString());
	final Optional<String> criteriaEndD = Optional.of(LocalDate.now().toString());

	final Optional<String> criteriaStartDEmpty = Optional.empty();
	final Optional<String> criteriaEndDEmpty = Optional.empty();

	final Optional<String> criteriaNameEmpty = Optional.empty();
	final Optional<String> criteriaTechnologyEmpty = Optional.empty();;
	final Optional<LocalDate> criteriaStartDateEmpty = Optional.empty();
	final Optional<LocalDate> criteriaEndDateEmpty = Optional.empty();

	final Optional<String> criteriaNameBlank = Optional.of(" ");
	final Optional<String> criteriaTechnologyBlank = Optional.of(" ");
	final Optional<String> criteriaStartDateBlank = Optional.of(" ");
	final Optional<String> criteriaEndDateBlank = Optional.of(" ");

	final Optional<String> criteriaNoStartDate = Optional.of("aversijala");
	final Optional<String> criteriaNoEndDate = Optional.of("aversijala");

	final LocalDate localDate = criteriaStartDate.get();
	final Date date = Date.from(localDate.atStartOfDay(ZoneId.of("UTC")).toInstant());

	Optional<String> name = Optional.empty();
	Optional<String> technology = Optional.empty();
	Optional<String> start_date = Optional.empty();
	Optional<String> end_date = Optional.empty();

	ResponseEntity<Object> reponseEntityObject = new ResponseEntity<>(simpleId, HttpStatus.CREATED);

	final Criteria criteriaEmpty = new Criteria();
	final Criteria criteria = new Criteria().and(stringName).is(criteriaName.get()).and(stringTechnology)
			.is(criteriaTechnology.get()).and(stringStartDate).is(date).and(stringEndDate).is(date);
	final Criteria criteria1 = new Criteria().and(sName).is(criteriaName.get()).and(stringTechnology)
			.is(criteriaTechnology.get()).and(stringStartDate).is(date).and(stringEndDate).is(date);
	final Criteria criteriaNoDates = new Criteria().and(sName).is(criteriaName.get()).and(stringTechnology)
			.is(criteriaTechnology.get());

}
