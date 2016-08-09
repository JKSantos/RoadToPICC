package com.gss.configurations;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class UpdateFowizConfigurations {
	
//	public static void main(String[] args){
//		setFowizConfigurations("santos.jeffrey0023", "6405021");
//	}

	  public static void setFowizConfigurations(String strUserName, String strPassCode) {

		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("resource/FowizSMSSenderSettings.properties");

			// set the properties value
			prop.setProperty("username", strUserName);
			prop.setProperty("passcode", strPassCode);

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	  }
	

}
