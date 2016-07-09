package com.gss.model;

import java.util.Date;

public class Payment {
	
	private int intPaymentID;
	private String strPaymentType;
	private double dblPaymentAmount;
	private Date datDateOfPayment;
	
	public Payment(int intPaymentID, String strPaymentType, double dblPaymentAmount, Date datDateOfPayment){
		
		this.intPaymentID = intPaymentID;
		this.strPaymentType = strPaymentType;
		this.dblPaymentAmount = dblPaymentAmount;
		this.datDateOfPayment = datDateOfPayment;
	}

	public int getIntPaymentID() {
		return intPaymentID;
	}

	public void setIntPaymentID(int intPaymentID) {
		this.intPaymentID = intPaymentID;
	}

	public String getStrPaymentType() {
		return strPaymentType;
	}

	public void setStrPaymentType(String strPaymentType) {
		this.strPaymentType = strPaymentType;
	}

	public double getDblPaymentAmount() {
		return dblPaymentAmount;
	}

	public void setDblPaymentAmount(double dblPaymentAmount) {
		this.dblPaymentAmount = dblPaymentAmount;
	}

	public Date getDatDateOfPayment() {
		return datDateOfPayment;
	}

	public void setDatDateOfPayment(Date datDateOfPayment) {
		this.datDateOfPayment = datDateOfPayment;
	}
}
