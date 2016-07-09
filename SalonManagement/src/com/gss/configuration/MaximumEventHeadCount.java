package com.gss.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MaximumEventHeadCount {
	
	public int getMaximum(){
		
		int intMax = 0;
		
		try{
			File file = new File("resource/MaximumHeadCount.properties");
			FileInputStream fin = new FileInputStream(file);
			
			Properties properties = new Properties();
			properties.load(fin);
			
			intMax = Integer.parseInt(properties.getProperty("MaximumHeadCount"));
		
			return intMax;
		}
		catch(Exception e){
			
			System.out.println(e.fillInStackTrace());
			return 0;
		}
	}
	
	public static void setMaximum(int maximum){
		
		try{
			Properties properties = new Properties();
			properties.setProperty("MaximumHeadCount", String.valueOf(maximum));
					
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
			
			File file = new File("resource/MaximumHeadCount.properties");
			FileOutputStream fout = new FileOutputStream(file);
			properties.store(fout, "As of " + format.format(date).toString());
			fout.close();
		}
		catch(Exception e)
		{
			System.out.println(e.fillInStackTrace());
		}
		
	}

}
