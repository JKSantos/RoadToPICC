package com.gss.model;

public class ServiceWalkIn {
	
	private int intServiceWalkInID;
	private Service service;
	private Employee employeeAssigned;
	private String strServiceStatus;
	
	public ServiceWalkIn(int intServiceWalkInID, Service service, Employee employeeAssigned, String strServiceStatus) {
		super();
		this.intServiceWalkInID = intServiceWalkInID;
		this.service = service;
		this.employeeAssigned = employeeAssigned;
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

	public Employee getEmployeeAssigned() {
		return employeeAssigned;
	}

	public void setEmployeeAssigned(Employee employeeAssigned) {
		this.employeeAssigned = employeeAssigned;
	}

	public String getStrServiceStatus() {
		return strServiceStatus;
	}

	public void setStrServiceStatus(String strServiceStatus) {
		this.strServiceStatus = strServiceStatus;
	}
	
	public ServiceWalkIn createNullServiceWalkIn(Service service, Employee employee){
		return new ServiceWalkIn(1, service, employee, "PENDING");
	}
}
