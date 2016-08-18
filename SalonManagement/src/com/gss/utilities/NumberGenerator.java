package com.gss.utilities;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

public class NumberGenerator {
	
	public static String localDateTime(){
		LocalDateTime now = LocalDateTime.now();
    	String year = String.valueOf(now.getYear());
    	String month = String.valueOf(now.getMonthValue());
    	String day = String.valueOf(now.getDayOfMonth());
    	String hour = String.valueOf(now.getHour());
    	String minute = String.valueOf(now.getMinute());
    	String second = String.valueOf(now.getSecond());
    	String millis = String.valueOf(now.get(ChronoField.MILLI_OF_SECOND));
    	
    	return hour + minute + second + millis + day + month + year;
	}

}
