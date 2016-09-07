package com.gss.actions.Reports;

import com.gss.model.ProductTagReport;
import com.gss.model.TagSum;

import java.sql.SQLException;
import java.util.List;

public class GetProductTagAction {
	
	private String dateFrom;
	private String dateTo;
	private List<ProductTagReport> report;
	private List<TagSum> tagSum;
	
	public String execute() throws SQLException{
		
		this.dateFrom = dateFrom.replaceAll("/0", "-");
		this.dateFrom = dateFrom.replaceAll("/", "-");
		this.dateTo = dateTo.replaceAll("/0", "-");
		this.dateTo = dateTo.replaceAll("/", "-");
		
		this.report = ProductTagReport.getProductTagReport(dateFrom, dateTo); 
		this.tagSum = TagSum.getTagSum(dateFrom, dateTo);
		
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
