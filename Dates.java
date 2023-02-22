package eu.barjak.java.MartonOmsz;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Dates implements GlobalVariables {
	
	public void elapsedDays(LocalDate startDate) {
		LocalDate tmpDate;
		LocalDate nowDate = LocalDate.now();
		long elapsed = Period.between(startDate, nowDate).getDays();
		for(int i = 0; i <= elapsed; i++) {
			tmpDate = startDate.plus(Period.ofDays(i));
			LOCALDATES.add(tmpDate);
		}
		for(LocalDate localDate : LOCALDATES) {
			TEMPERATURES_MAP.put(localDate, new ArrayList<Temperature>());
		}
	}
}