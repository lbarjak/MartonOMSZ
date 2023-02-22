package eu.barjak.java.MartonOmsz;

public class Temperature {
	
	private String time;
	private String day;
	private Double actualRoomTemp;
	private Double finalRoomTemp;
	private Double outdoorTemp;
	
	public Temperature() {
	}
	
	public Temperature(String time, String day, Double actualRoomTemp, Double finalRoomTemp, Double outdoorTemp){
		this.time = time;
		this.day = day;
		this.actualRoomTemp = actualRoomTemp;
		this.finalRoomTemp = finalRoomTemp;
		this.outdoorTemp = outdoorTemp;
	}
	
	public Double getOutTemp() {
		return this.outdoorTemp;
	}
	public void setOutTemp(Double outdoorTemp) {
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
	public Double getActualRoomTemp() {
		return this.actualRoomTemp;
	}
	public void setActualRoomTemp(Double actualRoomTemp) {
		this.actualRoomTemp = actualRoomTemp;
	}
	public Double getFinalRoomTemp() {
		return this.finalRoomTemp;
	}
	public void setFinalRoomTemp(Double finalRoomTemp) {
		this.finalRoomTemp = finalRoomTemp;
	}
	
}
