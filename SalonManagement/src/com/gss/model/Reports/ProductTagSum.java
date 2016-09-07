package com.gss.model.Reports;

import com.gss.service.ProductTagImpl;
import com.gss.service.ProductTags;

public class ProductTagSum {
	
	private String dateFrom;
	private String dateTo;
	private int totalDefective;
	private int totalLost;
	private int totalExpired;
	private int totalConsumed;
	
	public ProductTagSum(String dateFrom, String dateTo, int totalDefective, int totalLost, int totalExpired,
			int totalConsumed) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.totalDefective = totalDefective;
		this.totalLost = totalLost;
		this.totalExpired = totalExpired;
		this.totalConsumed = totalConsumed;
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
	public int getTotalDefective() {
		return totalDefective;
	}
	public void setTotalDefective(int totalDefective) {
		this.totalDefective = totalDefective;
	}
	public int getTotalLost() {
		return totalLost;
	}
	public void setTotalLost(int totalLost) {
		this.totalLost = totalLost;
	}
	public int getTotalExpired() {
		return totalExpired;
	}
	public void setTotalExpired(int totalExpired) {
		this.totalExpired = totalExpired;
	}
	public int getTotalConsumed() {
		return totalConsumed;
	}
	public void setTotalConsumed(int totalConsumed) {
		this.totalConsumed = totalConsumed;
	}
	public static ProductTagSum getProductTagSum(String dateFrom, String datTo){
		ProductTags service = new ProductTagImpl();
		
		return service.getProductTagSum(dateFrom, datTo);
	}
}
