package com.gss.model;

public class Customer {
	
	private int intID;
	private String strName;
	private String strAddress;
	private String strContactNo;
	private String strEmail;
	
	
	
	public Customer(int intID, String strName, String strAddress, String strContactNo, String strEmail) {
		super();
		this.intID = intID;
		this.strName = strName;
		this.strAddress = strAddress;
		this.strContactNo = strContactNo;
		this.strEmail = strEmail;
	}
	
	public int getIntID(){
		return intID;
	}
	public void setIntID(int intID){
		this.intID = intID;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getStrAddress() {
		return strAddress;
	}
	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}
	public String getStrContactNo() {
		return strContactNo;
	}
	public void setStrContactNo(String strContactNo) {
		this.strContactNo = strContactNo;
	}
	public String getStrEmail() {
		return strEmail;
	}
	public void setStrEmail(String strEmail) {
		this.strEmail = strEmail;
	}
}
