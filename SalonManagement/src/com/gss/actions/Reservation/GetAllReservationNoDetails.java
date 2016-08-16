package com.gss.actions.Reservation;

import java.util.List;

import com.gss.model.Reservation;

public class GetAllReservationNoDetails {

	private List<Reservation> reservationList;
	
	public String execute(){
		
		this.reservationList = Reservation.getAllReservationNoDetails();
		
		return "success";
	}

	public List<Reservation> getReservationList() {
		return reservationList;
	}
}
