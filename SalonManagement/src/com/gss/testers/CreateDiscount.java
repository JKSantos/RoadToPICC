package com.gss.testers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.gss.model.Discount;
import com.gss.service.DiscountServiceImpl;

public class CreateDiscount {
	
	public static void main(String[] args) throws ParseException {
		
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	    java.util.Date utilDate = format.parse("1996-5-6");
	    
	    String[] date = "1996-5-6".split("-");
	    
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Integer.parseInt(date[0]), (Integer.parseInt(date[1]) - 1), Integer.parseInt(date[2]));
	    long cal = calendar.getTimeInMillis();

	    java.util.Date dateee = new java.util.Date(cal);
	   
	    
	    System.out.println("utilDate:" + dateee);
	    System.out.println("sqlDate:" + sqlDate);
		

		

	  }
	
	public void set(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(cal.getTime());
		// Output "Wed Sep 26 14:23:28 EST 2012"

		String formatted = format1.format(cal.getTime());
		System.out.println(formatted);
		// Output "2012-09-26"
	
	}

}
