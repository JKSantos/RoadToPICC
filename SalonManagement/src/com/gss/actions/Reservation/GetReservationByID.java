package com.gss.actions.Reservation;

import com.gss.model.Reservation;

public class GetReservationByID {
		
	private int reservationID;
	private Reservation reservation;
	
	public String execute(){
		this.reservation = Reservation.getReservationByID(reservationID);
		
		return "success";
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
}
