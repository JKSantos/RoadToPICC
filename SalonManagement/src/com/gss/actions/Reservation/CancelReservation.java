package com.gss.actions.Reservation;

import java.sql.SQLException;

import com.gss.dao.ReservationJDBCRepository;

public class CancelReservation {
	
	private int intReservationID;
	
	public String execute(){
		
		boolean result;
		try {
			result = new ReservationJDBCRepository().cancelReservation(intReservationID);
			
			if(result == true)
				return "success";
			else
				return "failed";
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "failed";
		}
	}

	public void setIntReservationID(int intReservationID) {
		this.intReservationID = intReservationID;
	}
}
