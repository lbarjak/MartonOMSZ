package eu.barjak.java.MartonOmsz;

import java.time.LocalDate;

public class Writeout implements GlobalVariables {
	
	String time;
	Double outdoorTemp;
	Double roomTemp1;
	Double roomTemp2;
	
	public void toScreen() {
		for(Temperature temperature : TEMPERATURES) {
			time = temperature.getTime();
			outdoorTemp = temperature.getOutdoorTemp();
			roomTemp1 = temperature.getRoomTemp1();
			roomTemp2 = temperature.getRoomTemp2();
			System.out.println(time + "|" + outdoorTemp + "|" + roomTemp1 + "|" + roomTemp2);
		}
	}

}
