package com.gss.model;

public class EmployeeJob {
	
	private int intJobID;
	private String strTransType;
	private String strJobType;
	private String strJobDate;
	private String strCustomerName;
	private String strServiceName; 	
	private String strStatus;
	private int intTransID;
	
	public EmployeeJob(int intJobID, String strTransType, String strJobType, String strJobDate, String strCustomerName,
			String strServiceName, String strStatus) {
		super();
		this.intJobID = intJobID;
		this.strTransType = strTransType;
		this.strJobType = strJobType;
		this.strJobDate = strJobDate;
		this.strCustomerName = strCustomerName;
		this.strServiceName = strServiceName;
		this.strStatus = strStatus;
	}

	public int getIntJobID() {
		return intJobID;
	}

	public void setIntJobID(int intJobID) {
		this.intJobID = intJobID;
	}

	public String getStrTransType() {
		return strTransType;
	}

	public void setStrTransType(String strTransType) {
		this.strTransType = strTransType;
	}

	public String getStrJobType() {
		return strJobType;
	}

	public void setStrJobType(String strJobType) {
		this.strJobType = strJobType;
	}

	public String getStrJobDate() {
		return strJobDate;
	}

	public void setStrJobDate(String strJobDate) {
		this.strJobDate = strJobDate;
	}

	public String getStrCustomerName() {
		return strCustomerName;
	}

	public void setStrCustomerName(String strCustomerName) {
		this.strCustomerName = strCustomerName;
	}

	public String getStrServiceName() {
		return strServiceName;
	}

	public void setStrServiceName(String strServiceName) {
		this.strServiceName = strServiceName;
	}

	public String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}

	public int getIntTransID() {
		return intTransID;
	}

	public void setIntTransID(int intTransID) {
		this.intTransID = intTransID;
	}
}
