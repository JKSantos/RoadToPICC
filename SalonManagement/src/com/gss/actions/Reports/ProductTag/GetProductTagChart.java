package com.gss.actions.Reports.ProductTag;

import com.gss.dao.ProductTagJDBCRepository;
import com.gss.model.Reports.TagReport;
import com.gss.utilities.ReportsHelper;

public class GetProductTagChart {
	
	private TagReport report; 
	
	private String type; //monthly, annually, quarterly
	// annually
	private String yearFrom;
	private String yearTo;
	
	public String execute(){
		
		if(type.equalsIgnoreCase("annually"))	
			this.report = ProductTagJDBCRepository.getTagReport(ReportsHelper.annualReport(Integer.parseInt(yearFrom), Integer.parseInt(yearTo)), type);
		else if(type.equalsIgnoreCase("monthly"))	
			this.report = ProductTagJDBCRepository.getTagReport(ReportsHelper.monthlyReport(), type);
		else
			this.report = ProductTagJDBCRepository.getTagReport(ReportsHelper.quarterlyReport(), type);
		
		return "success";
	}

	public TagReport getReport() {
		return report;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public void setYearFrom(String yearFrom) {
		this.yearFrom = yearFrom;
	}

	public void setYearTo(String yearTo) {
		this.yearTo = yearTo;
	}

}
