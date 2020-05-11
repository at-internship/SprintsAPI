package com.sprints.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String message;
	private String path;
	private HttpStatus error;
	private int status;
	
	public EntityNotFoundException(String message, String path) {
		this.setMessage(message);
		this.setPath(path);
		this.setError(HttpStatus.NOT_FOUND);
		this.setStatus(404);
		
	}
}
