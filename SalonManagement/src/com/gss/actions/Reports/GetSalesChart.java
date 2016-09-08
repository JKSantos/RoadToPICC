package com.gss.actions.Reports;

import com.gss.dao.Reports.SalesReportRepository;
import com.gss.utilities.ReportsHelper;
import com.gss.model.Reports.SalesReport;

public class GetSalesChart {
	
	private SalesReport sales;
	
	private String type; //monthly, annually, quarterly
	//if anually
	private int yearFrom;
	private int yearTo;
	
	public String execute(){
		if(type.equalsIgnoreCase("monthly"))
			this.sales = SalesReportRepository.getSalesReport(ReportsHelper.monthlyReport(), type);
		else if(type.equalsIgnoreCase("annually"))
			this.sales = SalesReportRepository.getSalesReport(ReportsHelper.annualReport(yearFrom, yearTo), type);
		else
			this.sales = SalesReportRepository.getSalesReport(ReportsHelper.quarterlyReport(), type);
		
		return "success";
	}

	public SalesReport getSales() {
		return sales;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setYearFrom(int yearFrom) {
		this.yearFrom = yearFrom;
	}

	public void setYearTo(int yearTo) {
		this.yearTo = yearTo;
	}

}
