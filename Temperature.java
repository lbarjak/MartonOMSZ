package eu.barjak.java.MartonOmsz;

public class Temperature {
	
	private String time;
	private String day;
	private Float actualRoomTemp;
	private Float finalRoomTemp;
	
	public Temperature() {
	}
	
	public Temperature(String time, String day, Float actualRoomTemp, Float finalRoomTemp){
		this.time = time;
		this.day = day;
		this.actualRoomTemp = actualRoomTemp;
		this.finalRoomTemp = finalRoomTemp;
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
	public Float getActualRoomTemp() {
		return this.actualRoomTemp;
	}
	public void setActualRoomTemp(Float actualRoomTemp) {
		this.actualRoomTemp = actualRoomTemp;
	}
	public Float getFinalRoomTemp() {
		return this.finalRoomTemp;
	}
	public void setFinalRoomTemp(Float finalRoomTemp) {
		this.finalRoomTemp = finalRoomTemp;
	}

	
}
