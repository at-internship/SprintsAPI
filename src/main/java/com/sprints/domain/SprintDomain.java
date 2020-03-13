package com.sprints.domain;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SprintDomain {

	private String id;
	
	@Pattern(regexp = "\\A(?!\\s*\\Z).+")
	@Size(min = 1, message = "This field must contain something")
	@NotNull(message = "This field must be not null")
	private String name;
	private String technology;
	private Boolean active;
	private Boolean is_backlog;
	private LocalDate start_date;
	private LocalDate end_date;

}