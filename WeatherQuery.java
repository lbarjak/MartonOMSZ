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
	
	ArrayList<String> stringValues;
	ArrayList<Float> temperatureValues = new ArrayList<>();
	Float sum = 0f;
	int id = 615; //MartonOMSZ 590, MartonBambi 444, LagymanyosOMSZ 615
	String dateString = new Dates().now(0);
	
	public WeatherQuery() throws ParseException {
		String myTime = "23:50";
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date d = df.parse(myTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		for(int i=0; i<144; i++) {
			cal.add(Calendar.MINUTE, 10);
			String newTime = df.format(cal.getTime());
			//System.out.println("newTime: " + newTime);
			times.add(newTime);
		}
	}
	
	public void query() throws IOException, ParseException {
		
		System.out.println(dateString);
		//dateString = "2023-02-17";
		
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

		Pattern pattern = Pattern.compile("(?<=\\[).+(?=\\])");
		Matcher matcher = pattern.matcher(inputLine);
		while (matcher.find()) {
            stringValues = new ArrayList<String>(Arrays.asList(matcher.group().split(",")));
		}
		for (int i=0; i <= times.size()-1; i++) {//a times mérete változik a ciklusban, "szerencsére"
			if(!stringValues.get(i).equals("null")) {
				temperatureValues.add(Float.parseFloat(stringValues.get(i)));
				sum = sum + temperatureValues.get(i);
				System.out.print(times.get(i) + "|");
				System.out.println(temperatureValues.get(i));
			} else {//Ha null érték van szám helyett, kiveszük a hozzá tartozó idővel együtt
				times.remove(i);
				stringValues.remove(i--);
			}
		}
		System.out.println("average = " + sum/temperatureValues.size());
	}
}
