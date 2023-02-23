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
	int id = 590; //MartonOMSZ 590, MartonBambi 444, LagymanyosOMSZ 615
	LocalDate today = LocalDate.now();
	int indexOfTEMPERATURES = 0;
	
	public int steps() throws IOException, ParseException {
		for(LocalDate localDate : LOCALDATES) {
			if(!localDate.isAfter(today)) {
				query(localDate);
			}
		}
		return indexOfTEMPERATURES;
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

		for (int i = 0; i <= outdoorTemperatureString.size() - 1; i++) {
			if(!outdoorTemperatureString.get(i).equals("null")) {
				outdoorTemperature = Double.parseDouble(outdoorTemperatureString.get(i));
				TEMPERATURES_MAP.get(actualDate).get(i).setOutdoorTemp(outdoorTemperature);
				indexOfTEMPERATURES++;
//			} else {//Ha null érték van szám helyett, eldobjuk a POJO-t is
//				TEMPERATURES_MAP.get(actualDate).remove(i);
//				outdoorTemperatureString.remove(i--);
			}
		}
	}

}
