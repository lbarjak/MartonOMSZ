package eu.barjak.java.MartonOmsz;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
//import java.time.LocalTime;

public class App implements GlobalVariables {
	
	static String startDateString = "2023-02-18";
	static String startTimeString = "18:00";
	
    public static void main( String[] args ) throws IOException, ParseException {
    	
    	LocalDate startDate = LocalDate.parse(startDateString);
    	//LocalTime startTime = LocalTime.parse(startTimeString);
    	
    	Dates dates = new Dates();
		dates.elapsedDays(startDate);//startDate - nowDate --> LOCALDATES
		
		new WeatherQuery().steps();//LOCALDATES --> TEMPERATURES_MAP
		
		new Calculation();

		new Writeout().toScreen();

    }
    
}
