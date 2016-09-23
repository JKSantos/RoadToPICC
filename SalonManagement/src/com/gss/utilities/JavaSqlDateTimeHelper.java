package com.gss.utilities;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class JavaSqlDateTimeHelper {
	
	public static Date stringToDate(String dateString){
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/mm/dd");
		java.util.Date date;
		try {
			date = sdf1.parse(dateString);
			return new java.sql.Date(date.getTime()); 
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Time stringToTime(String timeString){
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		
		try {
		
		    java.util.Date d1 =(java.util.Date)format.parse(timeString);

		    return new java.sql.Time(d1.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
