package com.gss.utilities;

import java.sql.Time;

public class TimeHelper {
	
	public static Time parseTime(String strTime){
		
		Time time = new Time(0);
		
		String last = String.valueOf(strTime.charAt(strTime.length() - 1));
		String secondLast =  String.valueOf(strTime.charAt(strTime.length() -2));
		String meridian = secondLast + last;
		System.out.println(meridian);
		
		int length = strTime.length();
		
		strTime = strTime.substring(0, length - 2) + "";
		System.out.println(strTime);
		
		if(meridian.equals("AM")){
			
			String[] details = strTime.split(":");
			time.setHours(Integer.parseInt(details[0]));
			time.setMinutes(Integer.parseInt(details[1]));
			time.setSeconds(0);
		}
		else{
			
			String[] details = strTime.split(":");
			
			time.setHours((Integer.parseInt(details[0]) + 12));
			time.setMinutes(Integer.parseInt(details[1]));
			time.setSeconds(0);
		}
		
		System.out.println(time);
		return time;
		
	}
	
	public static Time parseTimeHomeService(String strTime){
		
		strTime = strTime.replace("AM", "");
		strTime = strTime.replace("PM", "");
		
		Time time = new Time(0);
		String[] array = strTime.split(":");
		
		time.setHours(Integer.parseInt(array[0]));
		time.setMinutes(Integer.parseInt(array[1]));
		time.setSeconds(0);
	
		return time;
		
	}
	
	

}
