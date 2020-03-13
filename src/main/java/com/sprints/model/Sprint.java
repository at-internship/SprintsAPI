package com.sprints.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Document(collection = "sprints")
@Data
public class Sprint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Pattern(regexp = "\\A(?!\\s*\\Z).+")
	@Size(min = 1, message = "This field must contain something")
	@NotNull(message = "This field must be not null")
	@Indexed(unique = true)
	private String name;
	
	private String technology;
	
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "is_backlog")
	private Boolean is_backlog;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	private LocalDate start_date;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	private LocalDate end_date;
}