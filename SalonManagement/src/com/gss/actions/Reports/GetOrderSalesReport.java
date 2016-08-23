package com.gss.actions.Reports;

import java.util.List;

import com.gss.dao.ReportsRepository;
import com.gss.model.ProductOrderSalesReport;
import com.gss.model.ReservationSalesReport;
import com.gss.model.WalkInSalesReport;

public class GetOrderSalesReport {
	
	//Filters
	private String dateFrom;
	private String dateTo;
	//Get
	private List<ProductOrderSalesReport> order;
	
	public String execute(){
		
		this.dateFrom += " 00:00:00";
		this.dateTo += " 23:59:59";
	
		this.order = ReportsRepository.getProductOrderSales(dateFrom, dateTo);
		
		return "success";
	}
	
	public List<ProductOrderSalesReport> getOrder() {
		return order;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
}
