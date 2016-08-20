package com.gss.actions.Reports;

import java.util.List;

import com.gss.dao.ReportsRepository;
import com.gss.model.ProductOrderSalesReport;
import com.gss.model.ReservationSalesReport;
import com.gss.model.WalkInSalesReport;

public class GetSalesReport {
	
	private List<ProductOrderSalesReport> order;
	private List<WalkInSalesReport> walkin;
	private List<ReservationSalesReport> reservation;
	
	public String execute(){
		
		this.order = ReportsRepository.getProductOrderSales();
		this.walkin = ReportsRepository.getWalkInSales();
		this.reservation = ReportsRepository.getReservationSales();
		
		return "success";
	}
	
	public List<ProductOrderSalesReport> getOrder() {
		return order;
	}
	public List<WalkInSalesReport> getWalkin() {
		return walkin;
	}
	public List<ReservationSalesReport> getReservation() {
		return reservation;
	}
	
	

}
