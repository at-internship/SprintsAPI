package com.sprints.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="sprints")
public class Sprint {
	@Id
	String id;
	public String getId() {
		return id;
	}



	String technology;
	boolean active;
	boolean is_backlog;
	Date start_date;
	Date end_date;
	
	
	
	public String getTechnology() {
		return technology;
	}



	public void setTechnology(String technology) {
		this.technology = technology;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	public boolean isIs_backlog() {
		return is_backlog;
	}



	public void setIs_backlog(boolean is_backlog) {
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

	

	@Override
	public String toString() {
		return "Sprint [technology=" + technology + ", active=" + active + ", is_backlog=" + is_backlog
				+ ", start_date=" + start_date + ", end_date=" + end_date + "]";
	}



	public Sprint(String id, String technology, boolean active, boolean is_backlog, Date start_date, Date end_date) {
		this.id = id;
		this.technology = technology;
		this.active = active;
		this.is_backlog = is_backlog;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	
}
