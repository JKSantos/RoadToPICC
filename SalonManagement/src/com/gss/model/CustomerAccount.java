package com.gss.model;

public class CustomerAccount {

	private int intCustID;
	private String strCustName;
	private String strContactNo;
	private String strEmail;
	private String strUsername;
	private String strPassword;
	
	public CustomerAccount(int intCustID, String strCustName, String strContactNo, String strEmail, String strUsername,
			String strPassword) {
		super();
		this.intCustID = intCustID;
		this.strCustName = strCustName;
		this.strContactNo = strContactNo;
		this.strEmail = strEmail;
		this.strUsername = strUsername;
		this.strPassword = strPassword;
	}
	public int getIntCustID() {
		return intCustID;
	}
	public void setIntCustID(int intCustID) {
		this.intCustID = intCustID;
	}
	public String getStrCustName() {
		return strCustName;
	}
	public void setStrCustName(String strCustName) {
		this.strCustName = strCustName;
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
	public String getStrUsername() {
		return strUsername;
	}
	public void setStrUsername(String strUsername) {
		this.strUsername = strUsername;
	}
	public String getStrPassword() {
		return strPassword;
	}
	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}
}
