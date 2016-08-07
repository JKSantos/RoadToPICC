package com.gss.actions.Payment;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.Payment;
import com.gss.model.ProductSales;
import com.gss.model.Reservation;

public class GetAllUnpaidTransactions {
	
	private List<ProductSales> orderList;
	private List<Reservation> reservationList;
	
	public String execute() throws SQLException{
		
		this.orderList = Payment.getAllUnpaidOrder();
		this.reservationList = Payment.getAllUnpaidReservation();
		
		return "success";
	}

	public List<ProductSales> getOrderList() {
		return orderList;
	}

	public List<Reservation> getReservationList() {
		return reservationList;
	}
}
