package com.gss.model;

public class Verification {
	
	private int intVerificationID;
	private String strVerificationCode;
	private int intCustomerID;
	
	public Verification(int intVerificationID, String strVerificationCode, int intCustomerID) {
		super();
		this.intVerificationID = intVerificationID;
		this.strVerificationCode = strVerificationCode;
		this.intCustomerID = intCustomerID;
	}

	public int getIntVerificationID() {
		return intVerificationID;
	}

	public void setIntVerificationID(int intVerificationID) {
		this.intVerificationID = intVerificationID;
	}

	public String getStrVerificationCode() {
		return strVerificationCode;
	}

	public void setStrVerificationCode(String strVerificationCode) {
		this.strVerificationCode = strVerificationCode;
	}

	public int getIntCustomerID() {
		return intCustomerID;
	}

	public void setIntCustomerID(int intCustomerID) {
		this.intCustomerID = intCustomerID;
	}
}
