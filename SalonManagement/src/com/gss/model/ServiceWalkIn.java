package com.gss.model;

public class ServiceWalkIn {
	
	private int intServiceWalkInID;
	private Service service;
	private int intEmployeeAssigned;
	private String strServiceStatus;
	
	public ServiceWalkIn(int intServiceWalkInID, Service service, int intEmployeeAssigned, String strServiceStatus){
		
		this.intServiceWalkInID = intServiceWalkInID;
		this.service = service;
		this.intEmployeeAssigned = intEmployeeAssigned;
		this.strServiceStatus = strServiceStatus;
	}

	public int getIntServiceWalkInID() {
		return intServiceWalkInID;
	}

	public void setIntServiceWalkInID(int intServiceWalkInID) {
		this.intServiceWalkInID = intServiceWalkInID;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public int getIntEmployeeAssigned() {
		return intEmployeeAssigned;
	}

	public void setIntEmployeeAssigned(int intEmployeeAssigned) {
		this.intEmployeeAssigned = intEmployeeAssigned;
	}

	public String getStrServiceStatus() {
		return strServiceStatus;
	}

	public void setStrServiceStatus(String strServiceStatus) {
		this.strServiceStatus = strServiceStatus;
	}
}
