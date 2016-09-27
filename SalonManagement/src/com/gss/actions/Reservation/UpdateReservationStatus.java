package com.gss.actions.Reservation;

import com.gss.dao.ReservationJDBCRepository;

public class UpdateReservationStatus {
	
	private String status;
	private int intReservationID;
	
	
	public String execute(){
		
		ReservationJDBCRepository repo = new ReservationJDBCRepository();
		repo.updateReservationStatus(intReservationID, status);

		return "success";
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public void setIntReservationID(int intReservationID) {
		this.intReservationID = intReservationID;
	}
}
