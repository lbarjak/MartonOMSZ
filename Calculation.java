package eu.barjak.java.MartonOmsz;

import java.time.LocalDate;

public class Calculation implements GlobalVariables {
	
	String time;
	String day;
	Double roomTemp1;
	Double roomTemp2;
	Double outdoorTemp;
		
	public void calculation(Double thermalTimeConstant, String startTimeString, Double initialRoomTemperature) {
		
		roomTemp1 = initialRoomTemperature;
		
		for(LocalDate localDate : LOCALDATES) {
			for(Temperature temperature : TEMPERATURES_MAP.get(localDate)) {
				TEMPERATURES.add(temperature);
			}
		}
			
		for(Temperature temperature : TEMPERATURES) {
//			time = temperature.getTime();
			temperature.setRoomTemp1(roomTemp1);
			outdoorTemp = temperature.getOutdoorTemp();
			tau();
			temperature.setRoomTemp2(roomTemp2);
			roomTemp1 = roomTemp2;
		}
	}
	
	public void tau() {
		roomTemp2 = outdoorTemp + (roomTemp1 - outdoorTemp) * 0.9966722;
	}

}
