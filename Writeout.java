package eu.barjak.java.MartonOmsz;

import java.time.LocalDate;

public class Writeout implements GlobalVariables {
	
	String time;
	String day;
	Double outdoorTemp;
	Double roomTemp1;
	Double roomTemp2;
	LocalDate date;
	
	public void toScreen() {
		for(Temperature temperature : TEMPERATURES) {
			time = temperature.getTime();
			day = temperature.getDay();
			outdoorTemp = temperature.getOutdoorTemp();
			roomTemp1 = temperature.getRoomTemp1();
			roomTemp2 = temperature.getRoomTemp2();
			date = temperature.getDate();
			System.out.println(date + "|" + time + "|" + day + "|" + outdoorTemp + "|" + roomTemp1);
		}
	}

}
