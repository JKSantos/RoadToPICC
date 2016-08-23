package com.gss.model;

public class WalkInTotalSales {
	
	private String dateFrom;
	private String dateTo;
	private double dblPrice;
	
	public WalkInTotalSales(String dateFrom, String dateTo, double dblPrice) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
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
	public double getDblPrice() {
		return dblPrice;
	}
	public void setDblPrice(double dblPrice) {
		this.dblPrice = dblPrice;
	}
}
