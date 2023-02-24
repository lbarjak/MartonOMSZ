package eu.barjak.java.MartonOmsz;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
		
		for(int i = 0; i < TEMPERATURES.size(); i++) {
			TEMPERATURES.get(i).setElapsedDays(i/144);
		}
			
		int startTimeIndex = searchStartTimeIndex(startTimeString);
		for(int i = startTimeIndex; i < TEMPERATURES.size(); i++) {
			TEMPERATURES.get(i).setRoomTemp1(roomTemp1);
			outdoorTemp = TEMPERATURES.get(i).getOutdoorTemp();
			if(outdoorTemp != null) {
				tau();
				TEMPERATURES.get(i).setRoomTemp2(roomTemp2);
				roomTemp1 = roomTemp2;
			}
		}
	}
	
	public void tau() {
		roomTemp2 = outdoorTemp + (roomTemp1 - outdoorTemp) * multiplier;
	}
	
	public int searchStartTimeIndex(String startTimeString) {
		String timeString;
		int startTimeIndex = 0;
		for(int i = 0; i < 144; i++) {
			timeString = TEMPERATURES.get(i).getTime();
			if(timeString.equals(startTimeString)) {
				startTimeIndex = i;
			}
		}
		return startTimeIndex;
	}
	
	public Double last24hAverage(int indexOfTEMPERATURES) {
		Double sum = 0.0;
		Double temp = 0.0;
		int divider = 144;
		for(int i = indexOfTEMPERATURES - 1; i > indexOfTEMPERATURES - 145; i--) {
			temp = TEMPERATURES.get(i).getOutdoorTemp();
			if(temp != null) {
				sum+= temp;
			} else {
				divider--;
			}
		}
		return sum/divider;
	}
	
	public void endIndex() {
		String dateTimeString;
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatNow = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		for(int i = 0; i < TEMPERATURES.size(); i++) {
			dateTimeString = TEMPERATURES.get(i).getDate().toString() 
					+ " " 
					+ TEMPERATURES.get(i).getTime();
			System.out.println(dateTimeString);
			System.out.println(formatNow.format(now).toString());
			//különbség?
		}
	}

}
