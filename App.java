package eu.barjak.java.MartonOmsz;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public class App implements GlobalVariables {
	
	static String startDateString = "2023-02-18";
	static String startTimeString = "17:00";
	static Double initialRoomTemperature = 17d;
	static final Double thermalTimeConstant = 50d;
	
    public static void main( String[] args ) throws IOException, ParseException {
    	
    	LocalDate startDate = LocalDate.parse(startDateString);
    	
    	Dates dates = new Dates();
		dates.elapsedDays(startDate);//startDate - nowDate --> LOCALDATES
		
		new WeatherQuery().steps();//LOCALDATES --> TEMPERATURES_MAP
		
		new Calculation().calculation(thermalTimeConstant, startTimeString, initialRoomTemperature);

		new Writeout().toScreen();

    }
    
}
