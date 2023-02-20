package eu.barjak.java.MartonOmsz;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class Dates {
	
	Locale huLoc = new Locale("hu");
    
    public String now(int difference) {
    	LocalDate now = LocalDate.now();
    	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
    	System.out.println(dtf.format(LocalDateTime.now()));
    	dtf = DateTimeFormatter.ofPattern("HH:mm");  
    	System.out.println(dtf.format(LocalDateTime.now()));
    	
    	DayOfWeek dow = now.getDayOfWeek();
    	String dayName = dow.getDisplayName(TextStyle.FULL, huLoc);
    	System.out.println(dayName);
    	return (now.plusDays(difference).toString());
    }
    public void diff(Date start, Date stop) {
        long diffSec = (stop.getTime() - start.getTime()) / 1000;
        System.out.println("--------------------");
        System.out.println(diffSec / 60 + " perc " + diffSec % 60 + " másodperc");
    }
}
//getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT