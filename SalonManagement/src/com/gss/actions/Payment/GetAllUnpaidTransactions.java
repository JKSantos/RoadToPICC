package com.gss.actions.Payment;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.Payment;
import com.gss.model.ProductSales;
import com.gss.model.Reservation;
import com.gss.model.WalkIn;

public class GetAllUnpaidTransactions {
	
	private List<ProductSales> orderList;
	private List<Reservation> reservationList;
	private List<WalkIn> walkinList;
	
	public String execute() throws SQLException{
		
		this.orderList = Payment.getAllUnpaidOrder();
		this.reservationList = Payment.getAllUnpaidReservation();
		this.walkinList = Payment.getAllUnpaidWalkIn();
		System.out.println(orderList.size()  +"..."+reservationList.size() +"..."+walkinList.size());
		
		return "success";
	}

	public List<ProductSales> getOrderList() {
		return orderList;
	}

	public List<Reservation> getReservationList() {
		return reservationList;
	}
	
	public List<WalkIn> getWalkinList(){
		return this.walkinList;
	}
}
