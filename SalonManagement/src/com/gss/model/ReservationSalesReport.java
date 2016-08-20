package com.gss.model;

import java.sql.Date;

public class ReservationSalesReport {
	
	private int intReservationID;
	private String strReservationType;
	private Date dtmDateTimeCreaetd;
	private String strName;
	private double dblInvoiceAmount;
	
	public ReservationSalesReport(int intReservationID, String strReservationType, Date dtmDateTimeCreaetd,
			String strName, double dblInvoiceAmount) {
		super();
		this.intReservationID = intReservationID;
		this.strReservationType = strReservationType;
		this.dtmDateTimeCreaetd = dtmDateTimeCreaetd;
		this.strName = strName;
		this.dblInvoiceAmount = dblInvoiceAmount;
	}
	public int getIntReservationID() {
		return intReservationID;
	}
	public void setIntReservationID(int intReservationID) {
		this.intReservationID = intReservationID;
	}
	public String getStrReservationType() {
		return strReservationType;
	}
	public void setStrReservationType(String strReservationType) {
		this.strReservationType = strReservationType;
	}
	public Date getDtmDateTimeCreaetd() {
		return dtmDateTimeCreaetd;
	}
	public void setDtmDateTimeCreaetd(Date dtmDateTimeCreaetd) {
		this.dtmDateTimeCreaetd = dtmDateTimeCreaetd;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public double getDblInvoiceAmount() {
		return dblInvoiceAmount;
	}
	public void setDblInvoiceAmount(double dblInvoiceAmount) {
		this.dblInvoiceAmount = dblInvoiceAmount;
	}
	public static String toString(int intType){
		if(intType == 1)
			return "HOME SERVICE";
		else
			return "EVENT";
	}
	
	
}
