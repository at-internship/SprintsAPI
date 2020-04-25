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
	final boolean booleanTrue = true;
	final boolean booleanFalse = false;

	final Optional<String> criteriaName = Optional.of("Sprint-Dummy");
    final Optional<String> criteriaTechnology = Optional.of("Postman");
    final Optional<LocalDate> criteriaStartDate = Optional.of(LocalDate.now());
    final Optional<LocalDate> criteriaEndDate = Optional.of(LocalDate.now());
   
    final Optional<String> criteriaNameEmpty = Optional.empty();
    final Optional<String> criteriaTechnologyEmpty = Optional.empty();;
    final Optional<LocalDate> criteriaStartDateEmpty = Optional.empty();
    final Optional<LocalDate> criteriaEndDateEmpty = Optional.empty();
   
    final LocalDate localDate = criteriaStartDate.get();
    final Date date = Date.from(localDate.atStartOfDay(ZoneId.of("UTC")).toInstant());
	
    final String stringTechnology = "java";
	final String stringStartDate = "2020-04-23";
	final String stringEndDate = "2020-04-30";
   
	 final Criteria criteria = new Criteria()
	            .and(stringName).is(criteriaName.get())
	            .and(stringTechnology).is(criteriaTechnology.get())
	            .and(stringStartDate).is(date)
	            .and(stringEndDate).is(date);
   
    final Criteria criteriaEmpty = new Criteria();
	
    Optional<String> name = Optional.empty();
	Optional<String> technology = Optional.empty();
	Optional<String> start_date = Optional.empty();
	Optional<String> end_date = Optional.empty();
	ResponseEntity<Object> reponseEntityObject = new ResponseEntity<>(simpleId, HttpStatus.CREATED);

}
