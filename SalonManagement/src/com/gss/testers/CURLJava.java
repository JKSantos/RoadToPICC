package com.gss.testers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;



import org.apache.commons.codec.binary.Base64;

import net.sf.json.JSONObject;

public class CURLJava {
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException{
//		URL url = new URL("http://smsgateway.me/api/v3/messages/send");
//
//		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
//		    for (String line; (line = reader.readLine()) != null;) {
//		        System.out.println(line);
//		    }
//		}
		
//		String gateway = "http://smsgateway.me/api/v3/devices";
//		String localhost = "localhost:8080/SalonManagement/";
//		
//		HttpURLConnection httpcon = (HttpURLConnection) ((new URL(gateway).openConnection()));
//		httpcon.setDoOutput(true);
//		httpcon.setRequestProperty("Content-Type", "application/json");
//		//httpcon.setRequestProperty("Accept", "application/json");
//		httpcon.setRequestMethod("POST");
//		String loginPassword = "'password':rEpLiCaTe6405021";
//		String encoded = new sun.misc.BASE64Encoder().encode (loginPassword.getBytes());
//		String param = "{'username':jeffrey,'pass':santos}";
//		String sample = "{'email':santos_jeffrey0023@gmail.com,'password':"+ encoded +"}";
//		String params = "{'email':santos_jeffrey0023@gmail.com,"+ loginPassword +",'device':26537,'number':+639361144842,'message':sample}";
//		httpcon.setRequestProperty ("Authorization", "Basic " + encoded);
//		JSONObject obj = new JSONObject();
//		obj.put("email", "santos_jeffrey0023@gmail.com");
//		obj.put("password", loginPassword);
//		
//		
//		byte[] outputBytes = params.getBytes("UTF-8");
////		OutputStreamWriter os = httpcon.getOutputStream();
//		OutputStreamWriter wr = new OutputStreamWriter(httpcon.getOutputStream());
//		wr.write(obj.toString());
//		
//		wr.flush();
//		
//		wr.close();
//		httpcon.connect();	
//		try (BufferedReader reader = new BufferedReader(new InputStreamReader((httpcon.getInputStream()), "UTF-8"))) {
//	    for (String line; (line = reader.readLine()) != null;) {
//	        System.out.println(line);
//	    }
//	}
		execute();
	}
	
	public static void execute() {

		try {
			String webPage = "https://localhost:8080/SalonManagement/";
			String gateway = "http://smsgateway.me/api/v3/devices";
			webPage = gateway;
			String name = "santos_jeffrey0023@gmail.com";
			String password = "rEpLiCaTe6405021";


			String authString = name + ":" + password;
			System.out.println("auth string: " + authString);
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			System.out.println("Base64 encoded auth string: " + authStringEnc);

			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			String result = sb.toString();

			System.out.println("*** BEGIN ***");
			System.out.println(result);
			System.out.println("*** END ***");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
