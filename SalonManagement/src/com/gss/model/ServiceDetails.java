package com.gss.model;

import java.util.List;

public class ServiceDetails {

	private int intServiceID;
	private int intQuantity;
	private int intEmployeeID;
	private String strStatus;
	
	public ServiceDetails(int intServiceID, int intQuantity, int intEmployeeID, String strStatus) {
		super();
		this.intServiceID = intServiceID;
		this.intQuantity = intQuantity;
		this.intEmployeeID = intEmployeeID;
		this.strStatus = strStatus;
	}

	public int getIntServiceID() {
		return intServiceID;
	}

	public void setIntServiceID(int intServiceID) {
		this.intServiceID = intServiceID;
	}

	public int getIntQuantity() {
		return intQuantity;
	}

	public void setIntQuantity(int intQuantity) {
		this.intQuantity = intQuantity;
	}

	public int getIntEmployeeID() {
		return intEmployeeID;
	}

	public void setIntEmployeeID(int intEmployeeID) {
		this.intEmployeeID = intEmployeeID;
	}

	public String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	
	
}
