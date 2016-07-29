package com.gss.actions.Payment;

import java.sql.SQLException;

import com.gss.model.Payment;

public class CreatePaymentAction {

	private Payment payment;
	private String paymentType;
	private String result = "failed";
	
	public String execute() throws SQLException{
		
		boolean recorded = Payment.createPayment(paymentType, payment);
		
		if(recorded == true)
			result = "success";
		
		return result;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getResult() {
		return result;
	}
}
