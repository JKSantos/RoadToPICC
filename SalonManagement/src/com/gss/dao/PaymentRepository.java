package com.gss.dao;

import com.gss.model.Payment;

public interface PaymentRepository {
	
	public boolean createReservationPayment(Payment payment);
	
	public boolean createWalkInPayment(Payment payment);
	
	public boolean createProductSalesPayment(Payment payment);
	
	public boolean refundPayment();

}
