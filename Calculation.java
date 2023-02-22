package eu.barjak.java.MartonOmsz;

import java.time.LocalDate;

public class Calculation implements GlobalVariables {
		
	public void calculation(String startTimeString, Float initialRoomTemperature, Float thermalTimeConstant) {
		
		String time;
		String day;
		Float actualRoomTemp;
		Float finalRoomTemp;
		Float outdoorTemp;
		
		for(LocalDate localDate : LOCALDATES) {
			//System.out.println(localDate);
			for(Temperature temperature : TEMPERATURES_MAP.get(localDate)) {
//				time = temperature.getTime();
//				outdoorTemp = temperature.getOutTemp();
//				System.out.println(time + "|" + outdoorTemp);
				TEMPERATURES.add(temperature);
			}
		}
	}

}
