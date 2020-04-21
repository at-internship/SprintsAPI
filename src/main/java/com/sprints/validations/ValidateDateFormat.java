package com.sprints.validations;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class ValidateDateFormat {

	public Date stringToDate(Optional<String> date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try {
			LocalDate local_Date = LocalDate.parse(date.get(), formatter);
			Date formattedDate = Date.from(local_Date.atStartOfDay(ZoneId.of("UTC")).toInstant());
			return formattedDate;
			
		} catch (Exception e) {
			return null;
		}

	}
}
