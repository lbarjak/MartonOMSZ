package eu.barjak.java.MartonOmsz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherQuery {
	
	ArrayList<String> labels = new ArrayList<String>(Arrays.asList("00:00","00:10","00:20","00:30","00:40","00:50","01:00","01:10","01:20","01:30","01:40","01:50","02:00","02:10","02:20","02:30","02:40","02:50","03:00","03:10","03:20","03:30","03:40","03:50","04:00","04:10","04:20","04:30","04:40","04:50","05:00","05:10","05:20","05:30","05:40","05:50","06:00","06:10","06:20","06:30","06:40","06:50","07:00","07:10","07:20","07:30","07:40","07:50","08:00","08:10","08:20","08:30","08:40","08:50","09:00","09:10","09:20","09:30","09:40","09:50","10:00","10:10","10:20","10:30","10:40","10:50","11:00","11:10","11:20","11:30","11:40","11:50","12:00","12:10","12:20","12:30","12:40","12:50","13:00","13:10","13:20","13:30","13:40","13:50","14:00","14:10","14:20","14:30","14:40","14:50","15:00","15:10","15:20","15:30","15:40","15:50","16:00","16:10","16:20","16:30","16:40","16:50","17:00","17:10","17:20","17:30","17:40","17:50","18:00","18:10","18:20","18:30","18:40","18:50","19:00","19:10","19:20","19:30","19:40","19:50","20:00","20:10","20:20","20:30","20:40","20:50","21:00","21:10","21:20","21:30","21:40","21:50","22:00","22:10","22:20","22:30","22:40","22:50","23:00","23:10","23:20","23:30","23:40","23:50"));
	ArrayList<String> values;
	
	public void query() throws IOException {
		
		URL url = new URL("https://www.metnet.hu/online-allomasok?sub=showosdata&ostid=590&date=2023-02-16");
		BufferedReader in;
		in = new BufferedReader(new InputStreamReader(url.openStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			if (inputLine.contains("data: [")) {
				//System.out.println(inputLine);
				break;
			}
		}
//		Pattern pattern = Pattern.compile("\\[(.+)\\]");
//		Matcher matcher = pattern.matcher(inputLine);
//		if (matcher.find()) {
//		    System.out.println(matcher.group(1));
//		}
//		System.out.println(inputLine.split("\\[(.+)\\]")[0]);
		Pattern pattern = Pattern.compile("(?<=\\[).+(?=\\])");
		Matcher matcher = pattern.matcher(inputLine);
		while (matcher.find()) {
            //System.out.println("Start index: " + matcher.start());
            //System.out.println(" End index: " + matcher.end() + " ");
            //System.out.println(matcher.group());
            values = new ArrayList<String>(Arrays.asList(matcher.group().split(",")));
		}
		for (int i=0; i <= labels.size()-1; i++) {
			System.out.print(labels.get(i) + " ");
			System.out.println(values.get(i) + " ");
		}
	}
}
