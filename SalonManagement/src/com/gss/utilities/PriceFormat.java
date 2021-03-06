package com.gss.utilities;

import java.text.NumberFormat;
import java.util.Locale;

public class PriceFormat {
	
	public static double convertToDouble(String price, String currency) throws Exception
    {
        NumberFormat format = NumberFormat.getInstance(Locale.US);

        System.out.println(currency);
        String validString = price.replaceAll(currency, "");
        
        Number number = format.parse(validString);
        
        double adjusted = (number.doubleValue() * 9) + number.doubleValue();
       
        return number.doubleValue();
        
    }

}
