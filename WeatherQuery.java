package eu.barjak.java.MartonOmsz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherQuery implements GlobalVariables {
	
	ArrayList<String> outdoorTemperatureString;
	Double outdoorTemperature;
	int id = 444; //MartonOMSZ 590, MartonBambi 444, LagymanyosOMSZ 615
	
	public void steps() throws IOException, ParseException {
		for(LocalDate localDate : LOCALDATES) {
			query(localDate);
		}
	}
	
	public void query(LocalDate actualDate) throws IOException, ParseException {
		URL url = new URL("https://www.metnet.hu/online-allomasok?sub=showosdata&ostid=" 
		+ id + "&date=" + actualDate.toString());
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
            outdoorTemperatureString = new ArrayList<String>(Arrays.asList(matcher.group().split(",")));
		}
		addTimes(actualDate);
		for (int i = 0; i <= outdoorTemperatureString.size() - 1; i++) {
			if(!outdoorTemperatureString.get(i).equals("null")) {
				outdoorTemperature = Double.parseDouble(outdoorTemperatureString.get(i));
				TEMPERATURES_MAP.get(actualDate).get(i).setOutdoorTemp(outdoorTemperature);
//			} else {//Ha null érték van szám helyett, eldobjuk a POJO-t is
//				TEMPERATURES_MAP.get(actualDate).remove(i);
//				outdoorTemperatureString.remove(i--);
			}
		}
	}
	
	public void addTimes(LocalDate actualDate) throws ParseException {
		String initTime = "23:50";
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date d = sdf.parse(initTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		for(int i = 0; i < 144; i++) {
			TEMPERATURES_MAP.get(actualDate).add(new Temperature());
			cal.add(Calendar.MINUTE, 10);
			String newTime = sdf.format(cal.getTime());
			TEMPERATURES_MAP.get(actualDate).get(i).setTime(newTime);
		}
	}
}
