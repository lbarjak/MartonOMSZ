package eu.barjak.java.MartonOmsz;

import java.time.LocalDate;

public class Writeout implements GlobalVariables {
	
	String time;
	Float outTemp;
	
	public void toScreen() {
		for(Temperature temperature : TEMPERATURES) {
			time = temperature.getTime();
			outTemp = temperature.getOutTemp();
			System.out.println(time + "|" + outTemp);
		}
	}

}
