package eu.barjak.java.MartonOmsz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Calculation implements GlobalVariables {
	
	String time;
	String day;
	Double roomTemp1;
	Double roomTemp2;
	Double outdoorTemp;
	Double multiplier;
	Double exponent;
	Locale huLoc = new Locale("hu");
	DateTimeFormatter napNeveMagyarul = DateTimeFormatter.ofPattern("EEEE", huLoc);
		
	public void calculation(Double thermalTimeConstant, String startTimeString, Double initialRoomTemperature) {
		
		roomTemp1 = initialRoomTemperature;
		exponent = -(10.0/60)/thermalTimeConstant;
		multiplier = Math.exp(exponent);
		
		for(LocalDate localDate : LOCALDATES) {
			for(Temperature temperature : TEMPERATURES_MAP.get(localDate)) {
				TEMPERATURES.add(temperature);
				temperature.setDate(localDate);
				day=localDate.format(DateTimeFormatter.ofPattern("EEEE", huLoc));
				temperature.setDay(day);
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
		
		last24Average();
	}
	
	public void tau() {
		roomTemp2 = outdoorTemp + (roomTemp1 - outdoorTemp) * multiplier;
	}
	
	public void last24Average() {
		Double sum = 0.0;
		Double temp = 0.0;
		for(int i = TEMPERATURES.size() - 1; i >= TEMPERATURES.size() - 144; i--) {
			temp = TEMPERATURES.get(i).getOutdoorTemp();
			sum+= temp;
		}
		System.out.println("Az utolsó 24 óra átlaga: " + sum/144);
	}

}
