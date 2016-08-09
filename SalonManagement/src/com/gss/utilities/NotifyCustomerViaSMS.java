package com.gss.utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class NotifyCustomerViaSMS implements Runnable{
	
	private String myMessage;
	private String myPasscode;
	private String myUsername;
	private String toPhoneNumber;
	private Thread thread;
	
	public void sendSMS(String message, String toPhoneNumber)
	 {    
		 this.myMessage = message.replaceAll(" ", "+");
		 this.toPhoneNumber = toPhoneNumber;
		 start();
	 }

	@Override
	public void run() {
		
		loadConfigurations();

	    HttpClient client = new DefaultHttpClient();
	    HttpGet request = new HttpGet("http://cloud.fowiz.com/api/message_http_api.php?username="+myUsername
	            + "&phonenumber="+toPhoneNumber+"&message="+myMessage+"&passcode="+myPasscode);
	    HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	    BufferedReader rd = null;
		try {
			rd = new BufferedReader
			  (new InputStreamReader(response.getEntity().getContent()));
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}

	    String line = "";
	    StringBuffer responses = new StringBuffer();
	    try {
			while ((line = rd.readLine()) != null) {
			          responses.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}   
	   
	    System.out.println(responses.toString());
	    stop();
	}
	
	public void stop(){
		this.thread = null;
	}
	
	public void start(){
	
		if(thread == null){
			thread = new Thread(this, "Mail");
			thread.start();
		}
		else{
			thread.start();
		}
	}
	
	public void loadConfigurations(){
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("resource/FowizSMSSenderSettings.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			this.myPasscode = prop.getProperty("passcode");
			this.myUsername = prop.getProperty("username");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
