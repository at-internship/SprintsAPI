package com.sprints.validations;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

@Component
public class ValidateQueryParams {
	
	public Criteria fillCriteriaWithParams(Optional<String> name, Optional<String> technology,
			Optional<LocalDate> start_date, Optional<LocalDate> end_date) {
		
		Criteria criteria = new Criteria();
		
		if(name.isPresent()) {
			criteria = criteria.and("name").is(name.get());
		}		
		if(technology.isPresent()) {
			criteria = criteria.and("technology").is(technology.get());
		}
		if(start_date.isPresent()) {
			LocalDate start_Date = start_date.get();
			Date startDate = Date.from(start_Date.atStartOfDay(ZoneId.of("UTC")).toInstant());
			
			criteria = criteria.and("start_date").is(startDate);
		}
		if(end_date.isPresent()) {
			LocalDate end_Date = end_date.get();
			Date endDate = Date.from(end_Date.atStartOfDay(ZoneId.of("UTC")).toInstant());
			
			criteria = criteria.and("end_date").is(endDate);
		}
		
		return criteria;
		
	}
}
