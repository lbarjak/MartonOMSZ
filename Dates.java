package eu.barjak.java.MartonOmsz;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Dates implements GlobalVariables {
	
	Locale huLoc = new Locale("hu");
	DateTimeFormatter napNeveMagyarul = DateTimeFormatter.ofPattern("EEEE", huLoc);
	
	public void elapsedDays(LocalDate startDate) {
		LocalDate tmpDate;
		LocalDate nowDate = LocalDate.now();
		long elapsed = Period.between(startDate, nowDate).getDays();
		for(int i = 0; i <= elapsed; i++) {
			tmpDate = startDate.plus(Period.ofDays(i));
			LOCALDATES.add(tmpDate);
		}
	}
}