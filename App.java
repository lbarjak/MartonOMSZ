package eu.barjak.java.MartonOmsz;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public class App implements GlobalVariables {
	
	static String startDateString = "2023-02-11";
	static String endDateString = "2023-02-26";
	static String startTimeString = "17:00";
	static Double initialRoomTemperature = 17d;
	static final Double thermalTimeConstant = 50d;
	
    public static void main( String[] args ) throws IOException, ParseException {
    	
    	LocalDate startDate = LocalDate.parse(startDateString);
    	LocalDate endDate = LocalDate.parse(endDateString);
    	
    	Dates dates = new Dates();
		dates.elapsedDays(startDate, endDate);//startDate - nowDate --> LOCALDATES
		
		WeatherQuery weatherQuery = new WeatherQuery();
		int indexOfTEMPERATURES = weatherQuery.steps();//LOCALDATES --> TEMPERATURES_MAP
		
		Calculation calculation = new Calculation();
		calculation.calculation(thermalTimeConstant, startTimeString, initialRoomTemperature);
		Double last24hAverage = calculation.last24hAverage(indexOfTEMPERATURES);
		System.out.println("last24hAverage " + last24hAverage);

		//new Writeout().toScreen();
		new Writeout().toCSV();

    }
    
}
