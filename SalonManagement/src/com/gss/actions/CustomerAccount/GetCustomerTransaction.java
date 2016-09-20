package com.gss.actions.CustomerAccount;

import java.util.List;

import com.gss.model.ProductSales;
import com.gss.model.Reservation;
import com.gss.model.WalkIn;

public class GetCustomerTransaction {
	
	private List<WalkIn> appointments;
	private List<Reservation> reservations;
	private List<ProductSales> orders;
	
	private int custID;
	
	public String appointments(){
		this.appointments = CustomerTransactions.getCustomerAppointment(custID);
		
		return "success";
	}
	
	public String reservations(){
		this.reservations = CustomerTransactions.getAllReservation(custID);
		
		return "success";
	}
	
	public String orders(){
		this.orders = CustomerTransactions.getAllProductSales();
		
		return "success";
	}
	
	public void setCustID(int custID) {
		this.custID = custID;
	}
	public List<WalkIn> getAppointments() {
		return appointments;
	}
	public List<Reservation> getReservations() {
		return reservations;
	}
	public List<ProductSales> getOrders() {
		return orders;
	}
}
