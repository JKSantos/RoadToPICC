package com.gss.model.Reports;

public class ReservationTotalSales {
	
	private String dateFrom;
	private String dateTo;
	private String reservationType;
	private double dblPrice;
	
	public ReservationTotalSales(String dateFrom, String dateTo, String reservationType, double dblPrice) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.reservationType = reservationType;
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
	public String getReservationType() {
		return reservationType;
	}
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
	public double getDblPrice() {
		return dblPrice;
	}
	public void setDblPrice(double dblPrice) {
		this.dblPrice = dblPrice;
	}
}
