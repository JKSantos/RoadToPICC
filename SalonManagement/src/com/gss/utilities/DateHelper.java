package com.gss.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	
	 public static Date parseDate(String date) {
	     String[] date1 = date.split("-");
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(date1[0]), (Integer.parseInt(date1[1]) - 1), Integer.parseInt(date1[2]));
		long cal = calendar.getTimeInMillis();

		java.util.Date dateee = new java.util.Date(cal);
		return dateee;
	 }
	
	 public Date convertToDate(String[] date){
		 
		 String month = "";
		 String day = date[1];
		 String year = date[2];
		 String newDate = year + "-";
		 
		 switch(date[0]){
		 	case "January": month = "1"; break;
		 	case "February": month = "2"; break;
		 	case "March": month = "3"; break;
		 	case "April": month = "4"; break;
		 	case "May": month = "5"; break;
		 	case "June": month = "6"; break;
		 	case "July": month = "7"; break;
		 	case "August": month = "8"; break;
		 	case "September": month = "9"; break;
		 	case "October": month = "10"; break;
		 	case "November": month = "11"; break;
		 	case "December": month = "12"; break;
		 }
		 
		 newDate += month + "-" + day;
		 Date converted = DateHelper.parseDate(newDate);
		 	
		
		 return converted;
		 
	 }
	 
	 public String convert(String[] date){
		 
		 String month = "";
		 String day = date[1];
		 String year = date[2];
		 String newDate = year + "-";
		 
		 boolean flag = true;
		 
		 switch(date[0]){
		 	case "January": month = "1"; break;
		 	case "February": month = "2"; break;
		 	case "March": month = "3"; break;
		 	case "April": month = "4"; break;
		 	case "May": month = "5"; break;
		 	case "June": month = "6"; break;
		 	case "July": month = "7"; break;
		 	case "August": month = "8"; break;
		 	case "September": month = "9"; break;
		 	case "October": month = "10"; break;
		 	case "November": month = "11"; break;
		 	case "December": month = "12"; break;
		 	default: flag = false;
		 }
			
		if(flag == false){
			 month = date[0];
		 }
		 
		 return newDate += month + "-" + day;
		 
	 }

	 public static String stringDate(){
		 
		 LocalDateTime now = LocalDateTime.now();
		 int year = now.getYear();
		 int intMonth = now.getMonthValue();
		 int day = now.getDayOfMonth();
		 String month = null;
		 
		 switch(intMonth){
		 	case 1: month = "January"; break;
		 	case 2: month = "February"; break;
		 	case 3: month = "March"; break;
		 	case 4: month = "April"; break;
		 	case 5: month = "May"; break;
		 	case 6: month = "June"; break;
		 	case 7: month = "July"; break;
		 	case 8: month = "August"; break;
		 	case 9: month = "September"; break;
		 	case 10: month = "October"; break;
		 	case 11: month = "November"; break;
		 	case 12: month = "December"; break;
		 }
		 
		 return (month + " " + String.valueOf(day) + ", " + String.valueOf(year)).toUpperCase();
		 
	 }

	 public static String stringDate(Date date){
		 
		 Calendar cal = Calendar.getInstance();
		 
		 cal.setTime(date);
		 
		 return String.valueOf(cal.get(Calendar.MONTH)) + "-" + String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + "-" + String.valueOf(cal.get(Calendar.YEAR));
	 }
	 
	 public static String stringToStringYearFirst(String date){
		 
		 String[] stringDate = date.split("-");
		 
		 String month = "";
		 String day = stringDate[2];
		 String year = stringDate[0];
		 
		 switch(Integer.parseInt(stringDate[1])){
		 	case 1: month = "January"; break;
		 	case 2: month = "February"; break;
		 	case 3: month = "March"; break;
		 	case 4: month = "April"; break;
		 	case 5: month = "May"; break;
		 	case 6: month = "June"; break;
		 	case 7: month = "July"; break;
		 	case 8: month = "August"; break;
		 	case 9: month = "September"; break;
		 	case 10: month = "October"; break;
		 	case 11: month = "November"; break;
		 	case 12: month = "December"; break;
		 }
		 
		 return month + " " + day + ", " + year;
	 }

	 public static String intMonthToString(int intMonth){
		 
		 String month = "";
		 
		 switch(intMonth){
		 	case 1: month = "Jan"; break;
		 	case 2: month = "Feb"; break;
		 	case 3: month = "Mar"; break;
		 	case 4: month = "Apr"; break;
		 	case 5: month = "May"; break;
		 	case 6: month = "Jun"; break;
		 	case 7: month = "Jul"; break;
		 	case 8: month = "Aug"; break;
		 	case 9: month = "Sep"; break;
		 	case 10: month = "Octr"; break;
		 	case 11: month = "Nov"; break;
		 	case 12: month = "Dec"; break;
		 }
		 
		 return month;
	 }
}
