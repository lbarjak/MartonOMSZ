package eu.barjak.java.MartonOmsz;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Writeout implements GlobalVariables {

	public void toCSV() throws FileNotFoundException {
		String time;
		String day;
		Double outdoorTemp;
		Double roomTemp1;
		LocalDate date;
		int elapsedDays;
		PrintWriter output = new PrintWriter("hofokok.csv");
		for (Temperature temperature : TEMPERATURES) {
			time = temperature.getTime();
			day = temperature.getDay();
			outdoorTemp = temperature.getOutdoorTemp();
			roomTemp1 = temperature.getRoomTemp1();
			date = temperature.getDate();
			elapsedDays = temperature.getElapsedDays();
			output.println(date + "|" + time + "|" + day + "|" + outdoorTemp + "|" + elapsedDays + "|" + roomTemp1);
		}
		output.close();
	}

}
