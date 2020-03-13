package com.sprints.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityConflictException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	private String message;
	
	public EntityConflictException(String message) {
		this.setMessage(message);
	}
}
