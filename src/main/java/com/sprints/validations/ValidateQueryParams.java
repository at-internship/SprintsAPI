package com.sprints.validations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.sprints.validations.ValidateDateFormat;

@Component
public class ValidateQueryParams {

	@Autowired
	private ValidateDateFormat validateDateFormat;

	public Criteria fillCriteriaWithParams(Optional<String> name, Optional<String> technology,
			Optional<String> start_date, Optional<String> end_date) {

		Criteria criteria = new Criteria();

		if (name.isPresent() && !name.get().trim().isEmpty()) {
			criteria = criteria.and("name").is(name.get());
		}
		if (technology.isPresent() && !technology.get().trim().isEmpty()) {
			criteria = criteria.and("technology").is(technology.get());
		}
		if (start_date.isPresent() && !start_date.get().trim().isEmpty()) {
			if (validateDateFormat.stringToDate(start_date) != null) {
				criteria = criteria.and("start_date").is(validateDateFormat.stringToDate(start_date));
			}
		}
		if (end_date.isPresent() && !end_date.get().trim().isEmpty()) {
			if (validateDateFormat.stringToDate(end_date) != null) {
				criteria = criteria.and("end_date").is(validateDateFormat.stringToDate(end_date));
			}
		}

		return criteria;

	}
}
