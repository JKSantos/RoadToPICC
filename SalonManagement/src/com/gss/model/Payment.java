package com.gss.model;

import java.util.Date;

import com.gss.service.PaymentService;
import com.gss.service.PaymentServiceImpl;

public class Payment {
	
	private int intPaymentID;
	private int intInvoiceID;
	private String strPaymentType;
	private double dblPaymentAmount;
	private Date datDateOfPayment;

	public Payment(int intPaymentID, int intInvoiceID, String strPaymentType, double dblPaymentAmount,
			Date datDateOfPayment) {
		super();
		this.intPaymentID = intPaymentID;
		this.intInvoiceID = intInvoiceID;
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

	public int getIntInvoiceID() {
		return intInvoiceID;
	}

	public void setIntInvoiceID(int intInvoiceID) {
		this.intInvoiceID = intInvoiceID;
	}
	
	public static String convertToString(int payment){
		
		String converted = "";
		
		switch(payment){
			case 1:
				converted = "DOWN PAYMENT";
				break;
			case 2:
				converted = "COMPLEMENTARY PAYMENT";
				break;
			case 3:
				converted = "FULL PAYMENT";
				break;
			default:
				converted = "Update Payment.convertToString()";
		}
		
		return converted;
	}
	
	public static boolean createPayment(String paymentType, Payment payment){
		
		PaymentService service = new PaymentServiceImpl();
		
		if(paymentType.equals("reservation")){
			return service.createReservationPayment(payment);
		}
		else if(paymentType.equals("walkin")){
			return service.createWalkInPayment(payment);
		}
		else
			return service.createProductSalesPayment(payment);
		
	}
}
