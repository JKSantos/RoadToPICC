package com.gss.actions.Payment;

import java.sql.SQLException;
import java.util.Date;

import com.gss.model.Payment;
import com.gss.utilities.DateHelper;

public class CreatePaymentAction {

	private int intPaymentID;			//dummy data
	private int intInvoiceID;			//real data
	private String strPaymentType;		//"order", "walkin", or "reservation"
	private double dblPaymentAmount;	//real data
	private String datDateOfPayment;		
	
	private String paymentType;
	private String result = "failed";
	
	public String execute() throws SQLException{
		
		String unconvertedDate = new DateHelper().convert(this.datDateOfPayment.split("/"));
		
		boolean recorded = Payment.createPayment(paymentType, new Payment(intPaymentID, intInvoiceID, strPaymentType, dblPaymentAmount, DateHelper.parseDate(unconvertedDate)));
		
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDatDateOfPayment() {
		return datDateOfPayment;
	}

	public void setDatDateOfPayment(String datDateOfPayment) {
		this.datDateOfPayment = datDateOfPayment;
	}
	
	
	
}