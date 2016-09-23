package com.gss.actions.Reports.Sales;

import java.util.List;

import com.gss.dao.Reports.SalesReportRepository;
import com.gss.utilities.ReportDate;
import com.gss.utilities.ReportsHelper;

public class GetTotalClients {
	
	private String currentWhat;
	private List<Integer> clients;
	
	public String execute(){
		
		ReportDate date = null;
		if(currentWhat.equalsIgnoreCase("month"))
			date = ReportsHelper.currentMonth();
		else
			date = ReportsHelper.currentWeek();
		
		this.clients = SalesReportRepository.getTotalClient(date);
		
		return "success";
	}

	public List<Integer> getClients() {
		return clients;
	}

	public void setCurrentWhat(String currentWhat) {
		this.currentWhat = currentWhat;
	}

}
