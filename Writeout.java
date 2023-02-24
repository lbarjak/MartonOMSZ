package eu.barjak.java.MartonOmsz;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Writeout implements GlobalVariables {
	
	String time;
	String day;
	Double outdoorTemp;
	Double roomTemp1;
	Double roomTemp2;
	LocalDate date;
	int elapsedDays;
	
	public void toCSV() throws FileNotFoundException {
		PrintWriter output = new PrintWriter("hofokok.csv");
		for(Temperature temperature : TEMPERATURES) {
			time = temperature.getTime();
			day = temperature.getDay();
			outdoorTemp = temperature.getOutdoorTemp();
			roomTemp1 = temperature.getRoomTemp1();
			roomTemp2 = temperature.getRoomTemp2();
			date = temperature.getDate();
			elapsedDays = temperature.getElapsedDays();
			output.println(date + "|" + time + "|" + day + "|" + outdoorTemp + "|" + elapsedDays + "|" + roomTemp1);
		}
		output.close();
	}

}
