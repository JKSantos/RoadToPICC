package com.gss.model.Reports;

import java.sql.Date;

public class WalkInSalesReport {
	
	private int intWalkInID;
	private Date date;
	private String strName;
	private	double dblAmount;
	
	
	
	public WalkInSalesReport(int intWalkInID, Date date, String strName, double dblAmount) {
		super();
		this.intWalkInID = intWalkInID;
		this.date = date;
		this.strName = strName;
		this.dblAmount = dblAmount;
	}
	public int getIntWalkInID() {
		return intWalkInID;
	}
	public void setIntWalkInID(int intWalkInID) {
		this.intWalkInID = intWalkInID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	
	
	

}
