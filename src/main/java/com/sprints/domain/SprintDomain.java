package com.sprints.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SprintDomain {

	private String id;
	private String name;
	private String technology;
	private Boolean active;
	private Boolean is_backlog;
	private LocalDate start_date;
	private LocalDate end_date;

}