package com.sprints.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.sprints.exception.EntityConflictException;
import com.sprints.exception.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleEntityNotFoundException(EntityNotFoundException exception) {
		
		return exception.getMessage();
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public String handleEntityConflictException(EntityConflictException exception) {
		return exception.getMessage();

	}
}
