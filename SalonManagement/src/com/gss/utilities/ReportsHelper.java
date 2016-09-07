package com.gss.utilities;

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

}
