package com.gss.service;

import java.sql.SQLException;
import java.util.List;

import com.gss.dao.PaymentJDBCRepositoryImpl;
import com.gss.dao.PaymentRepository;
import com.gss.model.Payment;
import com.gss.model.ProductSales;
import com.gss.model.Reservation;
import com.gss.model.WalkIn;

public class PaymentServiceImpl implements PaymentService{

	@Override
	public boolean createReservationPayment(Payment payment, String receipt) throws SQLException {
		
		PaymentRepository repo = new PaymentJDBCRepositoryImpl();
		
		return repo.createReservationPayment(payment, receipt);
	}

	@Override
	public boolean createWalkInPayment(Payment payment, String receipt) throws SQLException {
		PaymentRepository repo = new PaymentJDBCRepositoryImpl();
		
		return repo.createWalkInPayment(payment, receipt);
	}

	@Override
	public boolean createProductSalesPayment(Payment payment, String receipt) throws SQLException {
		PaymentRepository repo = new PaymentJDBCRepositoryImpl();
	
		return repo.createProductSalesPayment(payment, receipt);
	}

	@Override
	public boolean refundPayment() throws SQLException {
		PaymentRepository repo = new PaymentJDBCRepositoryImpl();
		
		return repo.refundPayment();
	}

	@Override
	public List<Reservation> getAllUnpaidReservation() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WalkIn> getAllUnpaidWalkIn() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductSales> getAllUnpaidOrder() throws SQLException {
		
		PaymentRepository repo = new PaymentJDBCRepositoryImpl();
		
		return repo.getAllUnpaidOrder();
	}

}
