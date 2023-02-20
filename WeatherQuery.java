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
	
	ArrayList<String> stringTempValues;
	Float outTemp;
	int id = 590; //MartonOMSZ 590, MartonBambi 444, LagymanyosOMSZ 615
	
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
			TEMPERATURES.get(i).setTime(newTime);
		}
	}
	
	public void query(String dateString) throws IOException, ParseException {
		
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
		//a stringTempValues mérete csökken a ciklusban, "szerencsére"
		for (int i = 0; i <= stringTempValues.size() - 1; i++) {
			if(!stringTempValues.get(i).equals("null")) {
				outTemp = Float.parseFloat(stringTempValues.get(i));
				TEMPERATURES.get(i).setOutTemp(outTemp);
			} else {//Ha null érték van szám helyett, eldobjuk a POJO-t is
				TEMPERATURES.remove(i);
				stringTempValues.remove(i--);
			}
		}
		new Writeout().toScreen();
	}
}
