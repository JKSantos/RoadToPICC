package com.gss.model;

public class Barangay {
	
	private int intBrgyID;
	private String strBrgyName;
	
	public Barangay(int intBrgyID, String strBrgyName){
		
		this.intBrgyID = intBrgyID;
		this.strBrgyName = strBrgyName;
		
	}

	public int getIntBrgyID() {
		return intBrgyID;
	}

	public void setIntBrgyID(int intBrgyID) {
		this.intBrgyID = intBrgyID;
	}

	public String getStrBrgyName() {
		return strBrgyName;
	}

	public void setStrBrgyName(String strBrgyName) {
		this.strBrgyName = strBrgyName;
	}
	
	

}
