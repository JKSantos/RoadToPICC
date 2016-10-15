package com.gss.model.Reports;

import java.sql.Date;

public class ProductPurchases {
	
	private int transactionID;
	private String type;
	private Date transactionDate;
	private String customerName;
	private String productName;
	private int quantity;
	
	public ProductPurchases(int transactionID, String type, Date transactionDate, String customerName, String productName,
			int quantity) {
		super();
		this.transactionID = transactionID;
		this.type = type;
		this.transactionDate = transactionDate;
		this.customerName = customerName;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
