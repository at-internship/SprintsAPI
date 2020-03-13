package com.sprints.model;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sprints.domain.SprintDomain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="sprints")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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