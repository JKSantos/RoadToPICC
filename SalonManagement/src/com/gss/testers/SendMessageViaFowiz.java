package com.gss.testers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;



public class SendMessageViaFowiz {
	
	public static void main(String[] args) throws IOException{
		 String myPasscode = "6405021";
	        String myUsername = "czrey23";    
	        String toPhoneNumber = "09361144842";
	        String myMessage = "HELLO!";

	        HttpClient client = new DefaultHttpClient();
	        HttpGet request = new HttpGet("http://cloud.fowiz.com/api/message_http_api.php?username="+myUsername
	                + "&phonenumber="+toPhoneNumber+"&message="+myMessage+"&passcode="+myPasscode);
	        HttpResponse response = client.execute(request);

	        BufferedReader rd = new BufferedReader
	          (new InputStreamReader(response.getEntity().getContent()));
	    
	        String line = "";
	        StringBuffer responses = new StringBuffer();
	        while ((line = rd.readLine()) != null) {
	                  responses.append(line);
	        }   
	       
	        System.out.println(responses.toString());
	}

}
