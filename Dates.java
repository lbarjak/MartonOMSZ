package eu.barjak.java.MartonOmsz;

import java.time.LocalDate;
import java.util.Date;

public class Dates {
    
    public String now(int difference) {
    	LocalDate now = LocalDate.now();
    	return now.plusDays(difference).toString();
    }
    public void diff(Date start, Date stop) {
        long diffSec = (stop.getTime() - start.getTime()) / 1000;
        System.out.println("--------------------");
        System.out.println(diffSec / 60 + " perc " + diffSec % 60 + " másodperc");
    }
}
//getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT