package com.sprints.model;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection="sprints")
@Data
public class Sprint {
	@Id
	String id;
	String technology;
	boolean active;
	boolean is_backlog;
	LocalDate start_date;
	LocalDate end_date;
}