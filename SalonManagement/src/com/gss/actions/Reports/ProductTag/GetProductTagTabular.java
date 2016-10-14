package com.gss.actions.Reports.ProductTag;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.ProductTagReport;
import com.gss.model.TagSum;
import com.gss.utilities.ReportDate;

public class GetProductTagTabular {
	
	//POST
	private String dateFrom;
	private String dateTo;
	
	//RETURNED
	private List<ProductTagReport> report;
	private List<TagSum> tagSum;
	
	public String execute(){
		
		ReportDate date = new ReportDate(dateFrom, dateTo);;
		
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

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	

}
