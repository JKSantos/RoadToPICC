package com.gss.model;

import java.util.List;

import com.gss.service.LocationService;
import com.gss.service.LocationServiceImpl;

public class Location {
	
	private int intLocationID;
	private String strBarangay;
	private String strCity;
	private double dblLocationPrice;
	private int intLocationStatus;
	private String stringPrice;
	
	public Location(int intLocationID, String strBarangay, String strCity, double dblLocationPrice, int intLocationStatus){
		
		this.intLocationID = intLocationID;
		this.strBarangay = strBarangay;
		this.strCity = strCity;
		this.dblLocationPrice = dblLocationPrice;
		this.intLocationStatus = intLocationStatus;
	}

	public int getIntLocationID() {
		return intLocationID;
	}

	public void setIntLocationID(int intLocationID) {
		this.intLocationID = intLocationID;
	}

	public double getDblLocationPrice() {
		return dblLocationPrice;
	}

	public void setDblLocationPrice(double dblLocationPrice) {
		this.dblLocationPrice = dblLocationPrice;
	}

	public int getIntLocationStatus() {
		return intLocationStatus;
	}

	public void setIntLocationStatus(int intLocationStatus) {
		this.intLocationStatus = intLocationStatus;
	}

	public String getStrBarangay() {
		return strBarangay;
	}

	public void setStrBarangay(String strBarangay) {
		this.strBarangay = strBarangay;
	}

	public String getStrCity() {
		return strCity;
	}

	public void setStrCity(String strCity) {
		this.strCity = strCity;
	}

	public static List<Location> queryAllLocation() {
		LocationService service = new LocationServiceImpl();
		
		return service.queryAllLocation();
	}

	public String getStringPrice() {
		return stringPrice;
	}

	public void setStringPrice(String stringPrice) {
		this.stringPrice = stringPrice;
	}
	
}
