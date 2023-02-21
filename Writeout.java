package eu.barjak.java.MartonOmsz;

import java.time.LocalDate;

public class Writeout implements GlobalVariables {
	
	String time;
	Float outTemp;
	
	public void toScreen() {
		for(LocalDate localDate : LOCALDATES) {
			System.out.println(localDate);
			for(Temperature temperature : TEMPERATURES_MAP.get(localDate)) {
				time = temperature.getTime();
				outTemp = temperature.getOutTemp();
				System.out.println(time + "|" + outTemp);
			}
		}
	}

}
