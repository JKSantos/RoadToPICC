package com.gss.actions.Reports.Sales;

import com.gss.dao.Reports.SalesReportRepository;
import com.gss.model.Reports.SalesReport;
import com.gss.utilities.ReportDate;
import com.gss.utilities.ReportsHelper;

public class DashBoardSales {
	
	private String currentWhat;
	private SalesReport sales;
	
	public String execute(){
		
		ReportDate date = null;
		
		if(currentWhat.equalsIgnoreCase("month"))
			date = ReportsHelper.currentMonth();
		else
			date = ReportsHelper.currentWeek();
		
		this.sales = SalesReportRepository.getSalesReport(date, currentWhat);

		return "success";
	}

	public SalesReport getSales() {
		return sales;
	}

	public void setCurrentWhat(String currentWhat) {
		this.currentWhat = currentWhat;
	}
}
