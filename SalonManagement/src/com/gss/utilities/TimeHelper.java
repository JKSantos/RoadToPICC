package com.gss.utilities;

import java.sql.Time;

public class TimeHelper {
	
	public static Time parseTime(String strTime){
		
		Time time = new Time(0);
		
		String last = String.valueOf(strTime.charAt(strTime.length() - 1));
		String secondLast =  String.valueOf(strTime.charAt(strTime.length() -2));
		String meridian = secondLast + last;
		
		
		if(meridian.equals("AM")){
			strTime.replaceAll("AM", "");
			String[] details = strTime.split(":");
			time.setHours(Integer.parseInt(details[0]));
			time.setMinutes(Integer.parseInt(details[1]));
			time.setSeconds(0);
		}
		else{
			strTime.replaceAll("PM", "");
			String[] details = strTime.split(":");
			
			time.setHours((Integer.parseInt(details[0]) + 12));
			time.setMinutes(Integer.parseInt(details[1]));
			time.setSeconds(0);
		}
		
		System.out.println(time);
		return time;
		
	}

}
