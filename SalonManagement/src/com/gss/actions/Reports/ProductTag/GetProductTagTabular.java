package com.gss.actions.Reports.ProductTag;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.ProductTagReport;
import com.gss.model.TagSum;
import com.gss.utilities.ReportDate;

public class GetProductTagTabular {
	
	//POST
	private int intMonth;
	private int intYear;
	
	//RETURNED
	private List<ProductTagReport> report;
	private List<TagSum> tagSum;
	
	public String execute(){
		
		String datFrom = intYear + "-" + intMonth + "-" + 1;
		String datTo = intYear + "-" + intMonth + "-" + 31;
		
		ReportDate date = new ReportDate(datFrom, datTo);;
		
		try {
			this.report = ProductTagReport.getProductTagReport(date.getDateFrom(), date.getDateTo());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		this.tagSum = TagSum.getTagSum(date.getDateFrom(), date.getDateTo());
		
		return "success";
	}

	public List<ProductTagReport> getReport() {
		return report;
	}

	public List<TagSum> getTagSum() {
		return tagSum;
	}

	public void setIntMonth(int intMonth) {
		this.intMonth = intMonth;
	}

	public void setIntYear(int intYear) {
		this.intYear = intYear;
	}
	
	

}
