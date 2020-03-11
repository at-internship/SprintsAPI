package com.sprints.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sprints")
public class Sprint {

	@Id
	private ObjectId _id;
	private int id;
	private String name;
	private String technology;
	private Boolean active;
	private Boolean is_backlog;
	private Date start_date;
	private Date end_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getIs_backlog() {
		return is_backlog;
	}

	public void setIs_backlog(Boolean is_backlog) {
		this.is_backlog = is_backlog;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Sprint(int id, String name, String technology, Boolean active, Boolean is_backlog, Date start_date,
			Date end_date) {
		super();
		this.id = id;
		this.name = name;
		this.technology = technology;
		this.active = active;
		this.is_backlog = is_backlog;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	
	public Sprint() {
		
	}
	
}