package com.gss.model.Reports;

import java.sql.Date;

public class Sales {
	
	private int transactionID;
	private String type;
	private String customerName;
	private Date transactionDate;
	private double totalPrice;
	
	public Sales(int transactionID, String type, String customerName, Date transactionDate, double totalPrice) {
		super();
		this.transactionID = transactionID;
		this.type = type;
		this.customerName = customerName;
		this.transactionDate = transactionDate;
		this.totalPrice = totalPrice;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
