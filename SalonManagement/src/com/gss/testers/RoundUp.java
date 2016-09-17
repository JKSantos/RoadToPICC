package com.gss.testers;

import java.text.DecimalFormat;

public class RoundUp {
	
	public static void main(String[] args){
		double roundOff = Math.round(10 * 100.561) / 100.0;
		
		System.out.println(roundOff);
		DecimalFormat df = new DecimalFormat("#.##");
		String formatted = df.format(2.0); 
		System.out.println(formatted); //prints 2.46
		double value = 2;
		
		String strDouble = String.format("%.2f", value); 
		System.out.println(strDouble); // print 2.00 DecimalFormat df = new DecimalFormat("#.##"); String formatted = df.format(2.00023); System.out.println(formatted); //prints 2

	}

}
