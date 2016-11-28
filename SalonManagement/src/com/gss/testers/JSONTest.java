package com.gss.testers;

import java.util.List;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONTest {
	
	public static void main(String args[]) throws ParseException{
		String jsonString = "[{\"name\":\"jeffrey\",\"age\":16},{\"name\":\"marvin\",\"age\":14}]";
		//JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
		
		//SampleJSON sample = (SampleJSON)new JSONParser().parse(jsonString);
		
		Gson gson = new Gson();
		
		List<SampleJSON> sample = gson.fromJson(jsonString, new TypeToken<List<SampleJSON>>(){}.getType());
		
		System.out.print(sample.get(0).getName() + " -- " + sample.get(1).getName());
	
	}

}
