package com.gss.actions.Reports.CancelledReservation;

import java.util.List;

import com.gss.dao.ReservationJDBCRepository;
import com.gss.model.Reservation;
import com.gss.utilities.ReportDate;

public class GetCancelledReservation {
	
	private String dateFrom;
	private String dateTo;
	
	private List<Reservation> reservations;
	
	public String execute(){
		
		ReportDate date = new ReportDate(dateFrom, dateTo);;
		
		this.reservations = new ReservationJDBCRepository().getCancelledReservation(date);
		
		return "success";
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
}
