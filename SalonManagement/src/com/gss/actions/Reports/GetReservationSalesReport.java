package com.gss.actions.Reports;

import java.util.List;

import com.gss.dao.ReportsRepository;
import com.gss.model.Reports.ProductOrderSalesReport;
import com.gss.model.Reports.ReservationSalesReport;
import com.gss.model.Reports.WalkInSalesReport;

public class GetReservationSalesReport {
	
	//Filters
	private String dateFrom;
	private String dateTo;
	//Get
	private List<ReservationSalesReport> reservation;
	
	public String execute(){
		
		this.dateFrom += " 00:00:00";
		this.dateTo += " 23:59:59";

		this.reservation = ReportsRepository.getReservationSales(dateFrom, dateTo);
		
		return "success";
	}
	
	public List<ReservationSalesReport> getReservation() {
		return reservation;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
}
