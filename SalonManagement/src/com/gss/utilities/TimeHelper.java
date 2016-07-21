package com.gss.utilities;

import java.sql.Time;

public class TimeHelper {
	
	public static Time parseTime(String strTime, String meridian){
		
		Time time = new Time(0);
		
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

}
