package com.gss.model;

import java.util.Date;

public class Invoice {
	
	private int intInvoiceID;
	private Date datInvoiceDate;
	private String strInvoiceTime;
	private String paymentStatus;
	
	public Invoice(int intInvoiceID, Date datInvoiceDate, String strInvoiceTime, String paymentStatus){
		
		this.intInvoiceID = intInvoiceID;
		this.datInvoiceDate = datInvoiceDate;
		this.strInvoiceTime = strInvoiceTime;
		this.paymentStatus = paymentStatus;
	}

	public int getIntInvoiceID() {
		return intInvoiceID;
	}

	public void setIntInvoiceID(int intInvoiceID) {
		this.intInvoiceID = intInvoiceID;
	}

	public Date getDatInvoiceDate() {
		return datInvoiceDate;
	}

	public void setDatInvoiceDate(Date datInvoiceDate) {
		this.datInvoiceDate = datInvoiceDate;
	}

	public String getStrInvoiceTime() {
		return strInvoiceTime;
	}

	public void setStrInvoiceTime(String strInvoiceTime) {
		this.strInvoiceTime = strInvoiceTime;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}
