package eu.barjak.java.MartonOmsz;

//import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class Dates {
	
	Locale huLoc = new Locale("hu");
    
    public String now(int difference) {
    	LocalDate now = LocalDate.now();
    	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd EEEE HH:mm:ss", huLoc);  
    	System.out.println(dtf.format(LocalDateTime.now()));
    	return (now.plusDays(difference).toString());
    }
    public void diff(Date start, Date stop) {
        long diffSec = (stop.getTime() - start.getTime()) / 1000;
        System.out.println("--------------------");
        System.out.println(diffSec / 60 + " perc " + diffSec % 60 + " másodperc");
    }
}
//getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT