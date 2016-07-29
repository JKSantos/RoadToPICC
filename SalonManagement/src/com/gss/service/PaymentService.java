package com.gss.service;

import com.gss.model.Payment;

public interface PaymentService {
	
	public boolean createReservationPayment(Payment payment);
	
	public boolean createWalkInPayment(Payment payment);
	
	public boolean createProductSalesPayment(Payment payment);
	
	public boolean refundPayment();

}
