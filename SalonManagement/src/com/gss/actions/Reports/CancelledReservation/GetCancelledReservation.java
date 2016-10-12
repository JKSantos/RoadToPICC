package com.gss.actions.Reports.CancelledReservation;

import java.util.List;

import com.gss.dao.ReservationJDBCRepository;
import com.gss.model.Reservation;
import com.gss.utilities.ReportDate;

public class GetCancelledReservation {
	
	private List<Reservation> reservations;
	
	private int intMonth;
	private int intYear;
	
	public String execute(){
		
		String datFrom = intYear + "-" + intMonth + "-" + 1 + " 00:00:00";
		String datTo = intYear + "-" + intMonth + "-" + 31 + " 23:59:59";
		
		ReportDate date = new ReportDate(datFrom, datTo);;
		
		this.reservations = new ReservationJDBCRepository().getCancelledReservation(date);
		
		return "success";
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setIntMonth(int intMonth) {
		this.intMonth = intMonth;
	}

	public void setIntYear(int intYear) {
		this.intYear = intYear;
	}
}
