package com.gss.model.Reports;

public class ProductPurchaseTotal {
	
	private String dateFrom;
	private String dateTo;
	private int productID;
	private String productName;
	private int totalReservation;
	private int totalOrder;
	private int totalWalkIn;
	
	public ProductPurchaseTotal(String dateFrom, String dateTo, int productID, String productName, int totalReservation,
			int totalOrder, int totalWalkIn) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.productID = productID;
		this.productName = productName;
		this.totalReservation = totalReservation;
		this.totalOrder = totalOrder;
		this.totalWalkIn = totalWalkIn;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getTotalReservation() {
		return totalReservation;
	}
	public void setTotalReservation(int totalReservation) {
		this.totalReservation = totalReservation;
	}
	public int getTotalOrder() {
		return totalOrder;
	}
	public void setTotalOrder(int totalOrder) {
		this.totalOrder = totalOrder;
	}
	public int getTotalWalkIn() {
		return totalWalkIn;
	}
	public void setTotalWalkIn(int totalWalkIn) {
		this.totalWalkIn = totalWalkIn;
	}
	
	
}
