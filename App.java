package eu.barjak.java.MartonOmsz;

import java.io.IOException;
import java.text.ParseException;

public class App 
{
    public static void main( String[] args ) throws IOException, ParseException {
    	new Dates().now(0);
    	new WeatherQuery().query("2023-02-20");

    }
}
