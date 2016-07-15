package com.gss.model;

public class ExtraCharge {
	
	private int intECID;
	private String strECName;
	private String strECDetails;
	private double dblECPrice;
	private int intECStatus;
	
	public ExtraCharge(int intECID, String strECName, String strECDetails, double dblECPrice, int intECStatus){
		this.intECID = intECID;
		this.strECName = strECName;
		this.strECDetails = strECDetails;
		this.dblECPrice = dblECPrice;
		this.intECStatus = intECStatus;
	}

	public int getIntECID() {
		return intECID;
	}

	public void setIntECID(int intECID) {
		this.intECID = intECID;
	}

	public String getStrECName() {
		return strECName;
	}

	public void setStrECName(String strECName) {
		this.strECName = strECName;
	}

	public String getStrECDetails() {
		return strECDetails;
	}

	public void setStrECDetails(String strECDetails) {
		this.strECDetails = strECDetails;
	}

	public int getIntECStatus() {
		return intECStatus;
	}

	public void setIntECStatus(int intECStatus) {
		this.intECStatus = intECStatus;
	}

	public double getDblECPrice() {
		return dblECPrice;
	}

	public void setDblECPrice(double dblECPrice) {
		this.dblECPrice = dblECPrice;
	}

}
