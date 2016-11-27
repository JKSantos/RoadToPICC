package com.gss.testers;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONTest {
	
	public static void main(String args[]) throws ParseException{
		String jsonString = "{\"intPackageID\":1, "
							+ "\"serviceList\":{"
								+ "\"intServiceID\":1, "
								+ "\"intQuantity\":1, "
								+ "\"intEmployeeID\":35, "
								+ "\"strStatus\"}"
							+ "}";
		JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
		
		System.out.println(jsonObject);
	
	}

}
