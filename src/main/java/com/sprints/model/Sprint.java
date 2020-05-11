package com.sprints.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "sprints")
@Data
public class Sprint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	private String name;
	
	private String technology;
	
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "is_backlog")
	private Boolean is_backlog;
	
	private LocalDate start_date;
	
	private LocalDate end_date;
}