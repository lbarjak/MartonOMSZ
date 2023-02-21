package eu.barjak.java.MartonOmsz;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface GlobalVariables {
	
	ArrayList<Temperature>TEMPERATURES = new ArrayList<>();
	ArrayList<LocalDate>LOCALDATES = new ArrayList<>();
	
	LinkedHashMap<LocalDate, ArrayList<Temperature>> TEMPERATURES_MAP = new LinkedHashMap<>();

}
