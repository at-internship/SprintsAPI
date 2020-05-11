package com.sprints.domain;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Sprint", description = "Information of the Sprint")
public class SprintDomain {

	@ApiModelProperty(notes = "Unique number of identification", name = "id", example = "5e9377c3e5303d599f55f987", required = true)
	private String id;

	@ApiModelProperty(notes = "Unique name of the Sprint", name = "name", example = "first Sprint", required = true)
	private String name;

	@ApiModelProperty(notes = "Technology to use", name = "technology", example = "Java", required = true)
	private String technology;

	@ApiModelProperty(notes = "Sprint status", name = "active", example = "true", required = true)
	private Boolean active;

	@ApiModelProperty(notes = "Task status", name = "is_backlog", example = "false", required = true)
	private Boolean is_backlog;

	@ApiModelProperty(notes = "Date to start the Sprint", name = "start_date", example = "2020-04-26", required = true)
	private LocalDate start_date;

	@ApiModelProperty(notes = "Deadline by the Sprint", name = "end_date", example = "2020-05-26", required = false)
	private LocalDate end_date;

}