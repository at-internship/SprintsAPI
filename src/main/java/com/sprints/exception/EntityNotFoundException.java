package com.sprints.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class EntityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String message;
	
	public EntityNotFoundException(String message) {
		this.setMessage(message);
	}
	
	
}
