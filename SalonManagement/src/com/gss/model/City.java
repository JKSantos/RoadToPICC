package com.gss.model;

public class City {
	
	private int intCityID;
	private String strCityName;
	
	public City(int intCityID, String strCityName){
		
		this.intCityID = intCityID;
		this.strCityName = strCityName;
	}

	public int getIntCityID() {
		return intCityID;
	}

	public void setIntCityID(int intCityID) {
		this.intCityID = intCityID;
	}

	public String getStrCityName() {
		return strCityName;
	}

	public void setStrCityName(String strCityName) {
		this.strCityName = strCityName;
	}
}
