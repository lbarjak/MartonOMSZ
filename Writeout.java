package eu.barjak.java.MartonOmsz;

import java.time.LocalDate;

public class Writeout implements GlobalVariables {
	
	String time;
	Double outdoorTemp;
	
	public void toScreen() {
		for(Temperature temperature : TEMPERATURES) {
			time = temperature.getTime();
			outdoorTemp = temperature.getOutTemp();
			System.out.println(time + "|" + outdoorTemp);
		}
	}

}
