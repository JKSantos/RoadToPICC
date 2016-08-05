package com.gss.testers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CURLJava {
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException{
//		URL url = new URL("http://smsgateway.me/api/v3/messages/send");
//
//		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
//		    for (String line; (line = reader.readLine()) != null;) {
//		        System.out.println(line);
//		    }
//		}
		
		HttpURLConnection httpcon = (HttpURLConnection) ((new URL("http://smsgateway.me/api/v3/messages/send").openConnection()));
		httpcon.setDoOutput(true);
		httpcon.setRequestProperty("Content-Type", "application/json");
		//httpcon.setRequestProperty("Accept", "application/json");
		httpcon.setRequestMethod("POST");
		String loginPassword = "'password':rEpLiCaTe6405021";
		String encoded = new sun.misc.BASE64Encoder().encode (loginPassword.getBytes());
		String params = "{'email':santos_jeffrey0023@gmail.com,"+ loginPassword +",'device':26537,'number':+639361144842,'message':sample}";
		//httpcon.setRequestProperty ("Authorization", "Basic " + encoded);
		httpcon.connect();	
		
		
		byte[] outputBytes = params.getBytes("UTF-8");
		OutputStream os = httpcon.getOutputStream();
		os.write(outputBytes);
		
		os.flush();
		
		os.close();
		
		int responseCode = httpcon.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);
	}

}
