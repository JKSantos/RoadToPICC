package com.gss.model.Reports;

import java.sql.Date;

public class ServiceAvailment {
	
	private int transID;
	private String type;
	private Date transDate;
	private String customerName;
	private String ServiceName;
	
	public ServiceAvailment(int transID, String type, Date transDate, String customerName, String serviceName) {
		super();
		this.transID = transID;
		this.type = type;
		this.transDate = transDate;
		this.customerName = customerName;
		ServiceName = serviceName;
	}
	public int getTransID() {
		return transID;
	}
	public void setTransID(int transID) {
		this.transID = transID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getServiceName() {
		return ServiceName;
	}
	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}
}
