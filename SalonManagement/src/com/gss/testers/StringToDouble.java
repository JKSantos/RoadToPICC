package com.gss.testers;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.*;
import java.util.*;

public class StringToDouble {
	
	


	    // Just for the sake of a simple test program!
	    public static void main(String[] args) throws Exception
	    {
	        NumberFormat format = NumberFormat.getInstance(Locale.US);
	        
	        String str = "PHP 22,000.2";
	        String validString = str.replaceAll("PHP ", "");
	        
	        Number number = format.parse(validString);
	        double price = number.doubleValue();
	        DecimalFormat df = new DecimalFormat("#.00"); 
	        
	        //System.out.println(price.df()); // or use number.doubleValue()
	    }
	    
	    public static double convertToTwoDecimal(double value, int places){
	    	if (places < 0) throw new IllegalArgumentException();

	        BigDecimal bd = new BigDecimal(value);
	        bd = bd.setScale(places, RoundingMode.HALF_UP);
	        return bd.doubleValue();
	    }
	    
	    public static String format(Number n) {
	        NumberFormat format = DecimalFormat.getInstance();
	        format.setRoundingMode(RoundingMode.FLOOR);
	        format.setMinimumFractionDigits(0);
	        format.setMaximumFractionDigits(2);
	        return format.format(n);
	    }
	

}
