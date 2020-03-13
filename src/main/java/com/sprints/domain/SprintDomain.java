package com.sprints.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SprintDomain {

	private String id;
	private String name;
	private String technology;
	private boolean active;
	private boolean is_backlog;
	private LocalDate start_date;
	private LocalDate end_date;

}