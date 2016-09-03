package com.gss.testers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;


public class ExportHighChartImage {
	


	

	    /**
	     * @param args
	     */
	    public static void main(String[] args) {
	        // TODO Auto-generated method stub

	        HttpPost post = new HttpPost("http://export.highcharts.com");


	        HttpClient client = HttpClientBuilder.create().build();

	        try{

	            String str="{'xAxis': {'categories': ['Jan', 'Feb', 'Mar']},'series': [{'data': [29.9, 71.5, 106.4]}]};";
	            String dataString = "{type:image/png,options:" + str+"}";
	             post.setEntity(new StringEntity(dataString, 
	                     ContentType.create("application/json")));

	        HttpResponse response = client.execute(post);

	        InputStream is = response.getEntity().getContent();


	        String filePath = "C:\\Java\\sample.png";
	        FileOutputStream fos = new FileOutputStream(new File(filePath));

	        int inByte;
	        while((inByte = is.read()) != -1) fos.write(inByte);
	        is.close();
	        fos.close();

	        System.out.println("Got Repsonse");
	        System.out.println(response);


	        }catch(Exception e){
	            System.out.println(e);
	        }



	    }
	}


