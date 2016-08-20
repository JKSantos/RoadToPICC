package com.gss.model;

import java.sql.Date;

public class ProductOrderSalesReport {

	private int intOrderID;
	private Date date;
	private String strType;
	private String strName;
	private double dblAmount;
	
	public ProductOrderSalesReport(int intOrderID, Date date, String strType, String strName, double dblAmount) {
		super();
		this.intOrderID = intOrderID;
		this.date = date;
		this.strType = strType;
		this.strName = strName;
		this.dblAmount = dblAmount;
	}
	public int getIntOrderID() {
		return intOrderID;
	}
	public void setIntOrderID(int intOrderID) {
		this.intOrderID = intOrderID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStrType() {
		return strType;
	}
	public void setStrType(String strType) {
		this.strType = strType;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public double getDblAmount() {
		return dblAmount;
	}
	public void setDblAmount(double dblAmount) {
		this.dblAmount = dblAmount;
	}
	public static String toString(int type){
		if(type == 1)
			return "DELIVERY";
		else
			return "PICKUP";
	}
	
}
