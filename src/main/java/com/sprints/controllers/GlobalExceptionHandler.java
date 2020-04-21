package com.sprints.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sprints.exception.ApiError;
import com.sprints.exception.EntityConflictException;
import com.sprints.exception.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
		return buildResponseEntity(
				new ApiError(exception.getError(), exception.getStatus(), exception.getMessage(), exception.getPath().toString()));
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> handleEntityConflictException(EntityConflictException exception) {
		return buildResponseEntity(
				new ApiError(exception.getError(), exception.getStatus(), exception.getMessage(), exception.getPath().toString()));

	}
	
}
