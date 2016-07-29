package com.gss.service;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.Payment;
import com.gss.model.ProductSales;
import com.gss.model.Reservation;
import com.gss.model.WalkIn;

public interface PaymentService {
	
	public boolean createReservationPayment(Payment payment) throws SQLException;
	
	public boolean createWalkInPayment(Payment payment) throws SQLException;
	
	public boolean createProductSalesPayment(Payment payment) throws SQLException;
	
	public boolean refundPayment() throws SQLException;
	
	public List<Reservation> getAllUnpaidReservation() throws SQLException ;
	
	public List<WalkIn> getAllUnpaidWalkIn() throws SQLException ;
	
	public List<ProductSales> getAllUnpaidOrder() throws SQLException ;

}
