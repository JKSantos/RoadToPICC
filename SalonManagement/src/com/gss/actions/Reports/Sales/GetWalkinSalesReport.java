package com.gss.actions.Reports.Sales;

import java.util.List;

import com.gss.dao.ReportsRepository;
import com.gss.model.Reports.ProductOrderSalesReport;
import com.gss.model.Reports.ReservationSalesReport;
import com.gss.model.Reports.WalkInSalesReport;

public class GetWalkinSalesReport {
	
	//Filters
	private String dateFrom;
	private String dateTo;
	//Get
	private List<WalkInSalesReport> walkin;
	
	public String execute(){
		
		this.dateFrom += " 00:00:00";
		this.dateTo += " 23:59:59";
		
		this.walkin = ReportsRepository.getWalkInSales(dateFrom, dateTo);
		
		return "success";
	}
	public List<WalkInSalesReport> getWalkin() {
		return walkin;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
}
