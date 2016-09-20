package com.gss.utilities;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportsHelper {
	
	public static List<ReportDate> monthlyReport(){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		int year = cal.get(Calendar.YEAR);
		List<ReportDate> dateList = new ArrayList<ReportDate>();
		
		for(int index = 1; index <= 12; index++){
			String dateFrom = year + "-" + index + "-" + 1;
			String dateTo   = year + "-" + index + "-" + 31;
			
			ReportDate report = new ReportDate(dateFrom, dateTo); 
			dateList.add(report);
		}
		
		return dateList;
	}
	
	public static List<ReportDate> quarterlyReport(){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		int year = cal.get(Calendar.YEAR);
		List<ReportDate> dateList = new ArrayList<ReportDate>();
		
		for(int index = 1; index <= 12; index++){
			String dateFrom = year + "-" + index + "-" + 1;
			String dateTo   = year + "-" + (index + 2) + "-" + 31;
			
			ReportDate report = new ReportDate(dateFrom, dateTo); 
			dateList.add(report);
			
			index += 2;
		}
		
		return dateList;
	}
	
	public static List<ReportDate> annualReport(int yearFrom, int yearTo){

		List<ReportDate> dateList = new ArrayList<ReportDate>();
		
		for(int index = yearFrom; index <= yearTo; index++){
			String dateFrom = index + "-" + 1 + "-" + 1;
			String dateTo   = index + "-" + 12 + "-" + 31;
			
			ReportDate report = new ReportDate(dateFrom, dateTo); 
			dateList.add(report);
		}
		
		return dateList;
	}

	public static ReportDate currentMonth(){
		
		ReportDate reportDate = new ReportDate("", "");
		
		Calendar c = Calendar.getInstance();
	    int year = c.get(Calendar.YEAR);
	    int month = c.get(Calendar.MONTH);
	    int day = 1;
	    c.set(year, month, day);
	    int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	    int fromDay = c.get(Calendar.DAY_OF_MONTH);
	    
	    c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth-1);
	    int toDay = c.get(Calendar.DAY_OF_MONTH);
	    
	    String fromDate = year+"-"+(month + 1)+"-"+fromDay;
	    String toDate = year+"-"+(month + 1)+"-"+toDay;
	    
	    reportDate.setDateFrom(fromDate);
	    reportDate.setDateTo(toDate);
	    
	    return reportDate;
	}

	
	public static ReportDate currentWeek(){
	  
		 // set the date
	    Calendar cal = Calendar.getInstance();

	    // "calculate" the start date of the week
	    Calendar first = (Calendar) cal.clone();
	    first.add(Calendar.DAY_OF_WEEK, 
	              first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));

	    // and add six days to the end date
	    Calendar last = (Calendar) first.clone();
	    last.add(Calendar.DAY_OF_YEAR, 6);

	    int year = cal.get(Calendar.YEAR);
	    int monthFrom = first.get(Calendar.MONTH) + 1;
	    int monthTo = last.get(Calendar.MONTH) + 1;
	    
	    String from = year+"-"+monthFrom+"-"+first.get(Calendar.DAY_OF_MONTH);
	    String to = year+"-"+monthTo+"-"+last.get(Calendar.DAY_OF_MONTH);
	    
	    ReportDate date = new ReportDate(from, to);
	    
	    System.out.println(from +" = " + to);
	    
	    
	    return date;
	}
	
	public static void main(String[] args){
		currentWeek();
	}
}
