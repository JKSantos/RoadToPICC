package com.gss.model.Reports;

import java.sql.Date;

public class ProductPurchases {
	
	private int transactionID;
	private Date transactionDate;
	private String transactionName;
	private String transactionType;
	private String productName;
	private int quantity;
	
	public ProductPurchases(int transactionID, Date transactionDate, String transactionName, String transactionType,
			String productName, int quantity) {
		super();
		this.transactionID = transactionID;
		this.transactionDate = transactionDate;
		this.transactionName = transactionName;
		this.transactionType = transactionType;
		this.productName = productName;
		this.quantity = quantity;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionName() {
		return transactionName;
	}
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	

}
