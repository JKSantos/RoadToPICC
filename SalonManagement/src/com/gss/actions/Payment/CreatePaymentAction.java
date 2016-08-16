package com.gss.actions.Payment;

import java.sql.SQLException;
import java.util.Date;

import com.gss.model.Payment;

public class CreatePaymentAction {

	private int intPaymentID;			//dummy data
	private int intInvoiceID;			//real data
	private String strPaymentType;		//"order", "walkin", or "reservation"
	private double dblPaymentAmount;	//real data
	private Date datDateOfPayment;
	
	private String paymentType;
	private String result = "failed";
	
	public String execute() throws SQLException{
		
		
		
		boolean recorded = Payment.createPayment(paymentType, new Payment(intPaymentID, intInvoiceID, strPaymentType, dblPaymentAmount, datDateOfPayment));
		
		if(recorded == true)
			result = "success";
		
		return result;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getResult() {
		return result;
	}

	public int getIntPaymentID() {
		return intPaymentID;
	}

	public void setIntPaymentID(int intPaymentID) {
		this.intPaymentID = intPaymentID;
	}

	public int getIntInvoiceID() {
		return intInvoiceID;
	}

	public void setIntInvoiceID(int intInvoiceID) {
		this.intInvoiceID = intInvoiceID;
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
}
