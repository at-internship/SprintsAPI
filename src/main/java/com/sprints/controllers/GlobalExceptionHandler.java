package com.sprints.controllers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sprints.exception.ApiError;
import com.sprints.exception.BadRequestException;
import com.sprints.exception.EntityConflictException;
import com.sprints.exception.EntityNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
			
		String[] statusArray = { "start_date", "end_date" };
		String error = "";
		
		for (int i = 0; i < statusArray.length; i++) {
			if (ex.toString().indexOf(statusArray[i]) == -1) {
				} else {
					if (statusArray[i].equals(ex.toString().substring(ex.toString().indexOf(statusArray[i]),
							ex.toString().indexOf(statusArray[i]) + statusArray[i].length()))) {
						error = "";
						error = ex.toString().substring(ex.toString().indexOf(statusArray[i]),
								ex.toString().indexOf(statusArray[i]) + statusArray[i].length());
						if (error.equals("start_date") || error.equals("end_date")) {
							error = "Malformed JSON request, format date should be: 'YYYY-MM-DD'; at " + error;
						}
						return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, 400, error, "/sprints/"));
					}
			}
		}
		String errorAlternative = "Malformed JSON request";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, 400, errorAlternative, "/sprints/"));
	}

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
	
	@ExceptionHandler
	public ResponseEntity<Object> handleBadRequestException(BadRequestException exception) {
		return buildResponseEntity(
				new ApiError(exception.getError(), exception.getStatus(), exception.getMessage(), exception.getPath().toString()));

	}
	
	

	
}
