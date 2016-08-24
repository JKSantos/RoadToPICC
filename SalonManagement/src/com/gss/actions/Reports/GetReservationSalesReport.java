package com.gss.actions.Reports;

import java.util.List;

import com.gss.dao.ReportsRepository;
import com.gss.model.Reports.ProductOrderSalesReport;
import com.gss.model.Reports.ProductOrderTotalSales;
import com.gss.model.Reports.ReservationSalesReport;
import com.gss.model.Reports.ReservationTotalSales;
import com.gss.model.Reports.WalkInSalesReport;
import com.gss.model.Reports.WalkInTotalSales;

public class GetReservationSalesReport {
	
	//Filters
	private String dateFrom;
	private String dateTo;
	//Get
	private List<ReservationSalesReport> reservation;
	private List<ReservationTotalSales> reservationTotal;
	private List<ProductOrderSalesReport> orderSales;
	private List<ProductOrderTotalSales> orderTotalSales;
	private List<WalkInSalesReport> walkinSales;
	private List<WalkInTotalSales> walkinTotal;
	
	public String execute(){
		
		this.dateFrom += " 00:00:00";
		this.dateTo += " 23:59:59";

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

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
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
