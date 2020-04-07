package com.sprints.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UndeclaredRequestParamException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String path;
	private HttpStatus error;
	private int status;
	
	public UndeclaredRequestParamException(String message, String path) {
		this.setMessage(message);
		this.setPath(path);
		this.setError(HttpStatus.BAD_REQUEST);
		this.setStatus(400);
	}

}
