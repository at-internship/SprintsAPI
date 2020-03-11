package com.sprints.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Document(collection="sprints")
@Data
public class Sprint {
	@Id
	String id;
	String technology;
	boolean active;
	boolean is_backlog;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	LocalDateTime start_date;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	LocalDateTime end_date;
}