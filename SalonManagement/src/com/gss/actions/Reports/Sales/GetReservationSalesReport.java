package com.gss.actions.Reports.Sales;

import java.util.List;

import com.gss.dao.ReportsRepository;
import com.gss.model.Reports.ProductOrderSalesReport;
import com.gss.model.Reports.ProductOrderTotalSales;
import com.gss.model.Reports.ReservationSalesReport;
import com.gss.model.Reports.ReservationTotalSales;
import com.gss.model.Reports.WalkInSalesReport;
import com.gss.model.Reports.WalkInTotalSales;
import com.gss.utilities.ReportDate;
import com.gss.utilities.ReportsHelper;

public class GetReservationSalesReport {
	
	//Filters
	private String currentWhat;

	//Get
	private List<ReservationSalesReport> reservation;
	private List<ReservationTotalSales> reservationTotal;
	private List<ProductOrderSalesReport> orderSales;
	private List<ProductOrderTotalSales> orderTotalSales;
	private List<WalkInSalesReport> walkinSales;
	private List<WalkInTotalSales> walkinTotal;
	
	public String execute(){
		
		ReportDate date = null;
		
		if(currentWhat.equalsIgnoreCase("month"))
			date = ReportsHelper.currentMonth();
		else
			date = ReportsHelper.currentWeek();
		
		String dateFrom = date.getDateFrom() + " 00:00:00";
		String dateTo = date.getDateTo() + " 23:59:59";

		this.reservation = ReportsRepository.getReservationSales(dateFrom, dateTo);	
		this.reservationTotal = ReportsRepository.getReservationTotalSales(dateFrom, dateTo);
		this.orderSales = ReportsRepository.getProductOrderSales(dateFrom, dateTo);
		this.orderTotalSales = ReportsRepository.getProductOrderTotalSales(dateFrom, dateTo);
		this.walkinSales = ReportsRepository.getWalkInSales(dateFrom, dateTo);
		this.walkinTotal = ReportsRepository.getWalkInTotalSales(dateFrom, dateTo);
		
		return "success";
	}
	
	public List<ReservationSalesReport> getReservation() {
		return reservation;
	}

	public void setCurrentWhat(String currentWhat) {
		this.currentWhat = currentWhat;
	}

	public List<ReservationTotalSales> getReservationTotal() {
		return reservationTotal;
	}

	public List<ProductOrderSalesReport> getOrderSales() {
		return orderSales;
	}

	public List<ProductOrderTotalSales> getOrderTotalSales() {
		return orderTotalSales;
	}

	public List<WalkInSalesReport> getWalkinSales() {
		return walkinSales;
	}

	public List<WalkInTotalSales> getWalkinTotal() {
		return walkinTotal;
	}
}
