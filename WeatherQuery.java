package eu.barjak.java.MartonOmsz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherQuery implements GlobalVariables {
	
	ArrayList<String> times = new ArrayList<>();
	
	ArrayList<String> stringTempValues;
	Float outTemp;
	ArrayList<Float> temperatureValues = new ArrayList<>();
	Float sum = 0f;
	int id = 590; //MartonOMSZ 590, MartonBambi 444, LagymanyosOMSZ 615
	String now = new Dates().now(0);
	
	public WeatherQuery() throws ParseException {
		String initTime = "23:50";
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date d = df.parse(initTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		for(int i=0; i<144; i++) {
			TEMPERATURES.add(new Temperature());
			cal.add(Calendar.MINUTE, 10);
			String newTime = df.format(cal.getTime());
			times.add(newTime);
			TEMPERATURES.get(i).setTime(newTime);
		}
	}
	
	public void query(String dateString) throws IOException, ParseException {
		
		System.out.println(dateString + " " + now);
		
		URL url = new URL("https://www.metnet.hu/online-allomasok?sub=showosdata&ostid=" 
		+ id + "&date=" + dateString);
		BufferedReader in;
		in = new BufferedReader(new InputStreamReader(url.openStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			if (inputLine.contains("data: [")) {
				break;
			}
		}

		Pattern pattern = Pattern.compile("(?<=\\[).+(?=\\])");//a grafikon hőmérsékletértékei
		Matcher matcher = pattern.matcher(inputLine);
		while (matcher.find()) {
            stringTempValues = new ArrayList<String>(Arrays.asList(matcher.group().split(",")));
		}
		//for (int i=0; i <= times.size()-1; i++) {//a times mérete változik a ciklusban, "szerencsére"
		for (int i=0; i <= TEMPERATURES.size()-1; i++) {
			if(!stringTempValues.get(i).equals("null")) {
				outTemp = Float.parseFloat(stringTempValues.get(i));
				temperatureValues.add(outTemp);
				TEMPERATURES.get(i).setOutTemp(outTemp);
				sum = sum + temperatureValues.get(i);
				System.out.print(times.get(i) + "|");
				System.out.println(temperatureValues.get(i));
			} else {//Ha null érték van szám helyett, kiveszük a hozzá tartozó idővel együtt
				times.remove(i);
				TEMPERATURES.remove(i);
				stringTempValues.remove(i--);
			}
		}
		System.out.println("average = " + sum/temperatureValues.size());
		new Writeout().toScreen();
	}
}
