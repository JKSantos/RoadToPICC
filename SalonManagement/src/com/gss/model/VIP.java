package com.gss.model;

import java.util.Date;

public class VIP {
	
	private String strVIPID;
	private String strVIPLastName;
	private String strVIPFirstName;
	private String strVIPMiddleName;
	private String strAddress;
	private String strContactNumber;
	private Date datDateCreated;
	private String strUsername;
	private String strPassword;
	private String strAccountStatus;
	
	public VIP(String strVIPID, String strVIPLastName, String strVIPFirstName, String strVIPMiddleName, String strAddress, String strContactNumber, Date datDateCreated, String strUsername, String strPassword, String strAccountStatus){
		
		this.strVIPID = strVIPID;
		this.strVIPLastName = strVIPLastName;
		this.strVIPFirstName = strVIPFirstName;
		this.strVIPMiddleName = strVIPMiddleName;
		this.strAddress = strAddress;
		this.strContactNumber = strContactNumber;
		this.datDateCreated = datDateCreated;
		this.strUsername = strUsername;
		this.strPassword = strPassword;
		this.strAccountStatus = strAccountStatus;
	}
	
	public String getStrVIPID() {
		return strVIPID;
	}
	public void setStrVIPID(String strVIPID) {
		this.strVIPID = strVIPID;
	}
	public String getStrVIPLastName() {
		return strVIPLastName;
	}
	public void setStrVIPLastName(String strVIPLastName) {
		this.strVIPLastName = strVIPLastName;
	}
	public String getStrVIPFirstName() {
		return strVIPFirstName;
	}
	public void setStrVIPFirstName(String strVIPFirstName) {
		this.strVIPFirstName = strVIPFirstName;
	}
	public String getStrVIPMiddleName() {
		return strVIPMiddleName;
	}
	public void setStrVIPMiddleName(String strVIPMiddleName) {
		this.strVIPMiddleName = strVIPMiddleName;
	}
	public String getStrAddress() {
		return strAddress;
	}
	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}
	public String getStrContactNumber() {
		return strContactNumber;
	}
	public void setStrContactNumber(String strContactNumber) {
		this.strContactNumber = strContactNumber;
	}
	public Date getDatDateCreated() {
		return datDateCreated;
	}
	public void setDatDateCreated(Date datDateCreated) {
		this.datDateCreated = datDateCreated;
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
	public String getStrAccountStatus() {
		return strAccountStatus;
	}
	public void setStrAccountStatus(String strAccountStatus) {
		this.strAccountStatus = strAccountStatus;
	}

}
