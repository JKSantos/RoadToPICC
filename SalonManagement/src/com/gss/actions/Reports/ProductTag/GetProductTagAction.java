package com.gss.actions.Reports.ProductTag;

import com.gss.model.ProductTagReport;
import com.gss.model.TagSum;
import com.gss.utilities.ReportDate;
import com.gss.utilities.ReportsHelper;

import java.sql.SQLException;
import java.util.List;

public class GetProductTagAction {
	
	private String currentWhat;
	
	private List<ProductTagReport> report;
	private List<TagSum> tagSum;
	
	public String execute() throws SQLException{
		
		ReportDate date = null;
		
		if(currentWhat.equalsIgnoreCase("month"))
			date = ReportsHelper.currentMonth();
		if(currentWhat.equalsIgnoreCase("week"))
			date = ReportsHelper.currentWeek();
		
		this.report = ProductTagReport.getProductTagReport(date.getDateFrom(), date.getDateTo()); 
		this.tagSum = TagSum.getTagSum(date.getDateFrom(), date.getDateTo());
		
		return "success";
	}

	public List<ProductTagReport> getReport() {
		return report;
	}
	
	public List<TagSum> getTagSum() {
		return tagSum;
	}

	public void setCurrentWhat(String currentWhat) {
		this.currentWhat = currentWhat;
	}
}
