package com.gss.utilities;

public class QuantityHelper {
	
	public static String[] removeEmptyQuantity(String[] quantity){
		
		String toBeConvertedAgain = "";
		int counter = 0;
		
		for(int ctr = 0; ctr < quantity.length; ctr++){
			if(!quantity[ctr].equals("") && counter == 0){
				toBeConvertedAgain += quantity[ctr];
				counter += 1;
			}
			else if(!quantity[ctr].equals("") && counter > 0){
				toBeConvertedAgain += "," + quantity[ctr];
				counter += 1;
			}
		}
		
		String[] split = toBeConvertedAgain.split(",");
		
		int[] i = new int[split.length];
		
		for(int ctr = 0; ctr < i .length; ctr++){
			System.out.print(split[ctr]);
			i[ctr] = Integer.parseInt(split[ctr]);}
			
		return split;
		
	}

}
