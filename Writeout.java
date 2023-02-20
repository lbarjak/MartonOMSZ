package eu.barjak.java.MartonOmsz;

public class Writeout implements GlobalVariables {
	
	String time;
	Float outTemp;
	
	public void toScreen() {
		
		for(int i = 0; i < TEMPERATURES.size(); i++) {
			time = TEMPERATURES.get(i).getTime();
			outTemp = TEMPERATURES.get(i).getOutTemp();
			System.out.println(time + "|" + outTemp);
		}
	}

}
