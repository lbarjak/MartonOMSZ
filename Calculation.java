package eu.barjak.java.MartonOmsz;

import java.time.LocalDate;

public class Calculation implements GlobalVariables {
	
	public Calculation(Double thermalTimeConstant) {
		
	}
	
	String time;
	String day;
	Double actualRoomTemp1;
	Double actualRoomTemp2;
	Double finalRoomTemp;
	Double outdoorTemp;
		
	public void calculation(String startTimeString, Double initialRoomTemperature) {
		
		for(LocalDate localDate : LOCALDATES) {
			//System.out.println(localDate);
			for(Temperature temperature : TEMPERATURES_MAP.get(localDate)) {
//				time = temperature.getTime();
//				outdoorTemp = temperature.getOutTemp();
//				System.out.println(time + "|" + outdoorTemp);
				TEMPERATURES.add(temperature);
			}
			TEMPERATURES.get(0).setActualRoomTemp(initialRoomTemperature);
		}
		for(Temperature temperature : TEMPERATURES) {
//			time = temperature.getTime();
//			outdoorTemp = temperature.getOutTemp();
		}
	}
	
	public Double tau() {
		return actualRoomTemp2 = outdoorTemp + (actualRoomTemp1 - outdoorTemp) * Math.pow(2.71828, (-(10/60)/50));
	}

}
