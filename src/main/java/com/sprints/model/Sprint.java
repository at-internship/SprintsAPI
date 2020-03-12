package com.sprints.model;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection="sprints")
@Data
public class Sprint {
	@Id
	private String id;
	private String name;
	private String technology;
	private boolean active;
	private boolean is_backlog;
	private LocalDate start_date;
	private LocalDate end_date;
}