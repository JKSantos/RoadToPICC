package com.gss.model.Reports;

public class ProductDetail {
	
	private String strProductName;
	private double totalSales;
	
	public ProductDetail(String strProductName, double totalSales) {
		super();
		this.strProductName = strProductName;
		this.totalSales = totalSales;
	}
	public String getStrProductName() {
		return strProductName;
	}
	public void setStrProductName(String strProductName) {
		this.strProductName = strProductName;
	}
	public double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}
}
