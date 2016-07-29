package com.gss.service;

import com.gss.dao.PaymentJDBCRepositoryImpl;
import com.gss.dao.PaymentRepository;
import com.gss.model.Payment;

public class PaymentServiceImpl implements PaymentService{

	@Override
	public boolean createReservationPayment(Payment payment) {
		
		PaymentRepository repo = new PaymentJDBCRepositoryImpl();
		
		return repo.createReservationPayment(payment);
	}

	@Override
	public boolean createWalkInPayment(Payment payment) {
		PaymentRepository repo = new PaymentJDBCRepositoryImpl();
		
		return repo.createWalkInPayment(payment);
	}

	@Override
	public boolean createProductSalesPayment(Payment payment) {
		PaymentRepository repo = new PaymentJDBCRepositoryImpl();
	
		return repo.createProductSalesPayment(payment);
	}

	@Override
	public boolean refundPayment() {
		PaymentRepository repo = new PaymentJDBCRepositoryImpl();
		
		return repo.refundPayment();
	}

}
