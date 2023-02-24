package eu.barjak.java.MartonOmsz;

import java.time.Duration;
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
	
	public int endIndex() {
		String dateTimeString;
		LocalDateTime dateTime;
		LocalDateTime now = LocalDateTime.now();
		long duration;
		int i;
		for(i = 0; i < TEMPERATURES.size(); i++) {
			dateTimeString = TEMPERATURES.get(i).getDate().toString() 
					+ "T" 
					+ TEMPERATURES.get(i).getTime();
			dateTime = LocalDateTime.parse(dateTimeString);
			duration = Duration.between(now, dateTime).toMinutes();
			if(Math.abs(duration) < 10) {
				break;
			}
		}
		return i;
	}
	
	public void forecast(int endIndex, Double last24hAverage) {
		roomTemp1 = TEMPERATURES.get(endIndex-1).getRoomTemp2();
		for(int i = endIndex; i < TEMPERATURES.size(); i++) {
			TEMPERATURES.get(i).setOutdoorTemp(last24hAverage);
			TEMPERATURES.get(i).setRoomTemp1(roomTemp1);
			outdoorTemp = TEMPERATURES.get(i).getOutdoorTemp();
			if(outdoorTemp != null) {
				tau();
				TEMPERATURES.get(i).setRoomTemp2(roomTemp2);
				roomTemp1 = roomTemp2;
			}
		}
	}

}
