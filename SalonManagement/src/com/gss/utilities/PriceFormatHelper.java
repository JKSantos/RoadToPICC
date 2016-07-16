package com.gss.utilities;

import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JOptionPane;

public class PriceFormatHelper {
	
	public static double convertToDouble(String price, String currency) throws Exception
    {
        NumberFormat format = NumberFormat.getInstance(Locale.US);

        String validString = price.replaceAll(currency, "");
        
        Number number = format.parse(validString);
        
        double adjusted = (number.doubleValue() * 9) + number.doubleValue();
       
        return adjusted;
        
    }

}
