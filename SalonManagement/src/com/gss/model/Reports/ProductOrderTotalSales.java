package com.gss.model.Reports;

public class ProductOrderTotalSales {
	
	private String dateFrom;
	private String dateTo;
	private String orderType;
	private double dblPrice;
	
	public ProductOrderTotalSales(String dateFrom, String dateTo, String orderType, double dblPrice) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.orderType = orderType;
		this.dblPrice = dblPrice;
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
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public double getDblPrice() {
		return dblPrice;
	}
	public void setDblPrice(double dblPrice) {
		this.dblPrice = dblPrice;
	}
}
