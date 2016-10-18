package com.gss.actions.Reports.Sales;

import java.util.List;

import com.gss.dao.Reports.SalesReportRepository;
import com.gss.model.Reports.Sales;
import com.gss.utilities.ReportDate;

public class GetSalesReportTabular {
	
	private List<Sales> sales;

	private String dateFrom;
	private String dateTo;
	
	public String execute(){
		ReportDate date = new ReportDate(dateFrom, dateTo);
		this.sales = SalesReportRepository.getSales(date);
		
		return "success";
	}

	public List<Sales> getSales() {
		return sales;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
}
