package eu.barjak.java.MartonOmsz;

public class Temperature {
	
	private String time;
	private String day;
	private Double roomTemp1;
	private Double roomTemp2;
	private Double outdoorTemp;
	
	public Temperature() {
	}
	
	public Temperature(String time, String day, Double roomTemp1, Double roomTemp2, Double outdoorTemp){
		this.time = time;
		this.day = day;
		this.roomTemp1 = roomTemp1;
		this.roomTemp2 = roomTemp2;
		this.outdoorTemp = outdoorTemp;
	}
	
	public Double getOutdoorTemp() {
		return this.outdoorTemp;
	}
	public void setOutdoorTemp(Double outdoorTemp) {
		this.outdoorTemp = outdoorTemp;
	}
	public String getTime() {
		return this.time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDay() {
		return this.day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Double getRoomTemp1() {
		return this.roomTemp1;
	}
	public void setRoomTemp1(Double roomTemp1) {
		this.roomTemp1 = roomTemp1;
	}
	public Double getRoomTemp2() {
		return this.roomTemp2;
	}
	public void setRoomTemp2(Double roomTemp2) {
		this.roomTemp2 = roomTemp2;
	}
	
}
