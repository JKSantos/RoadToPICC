package com.gss.actions.Reports;

import com.gss.model.ProductTagReport;
import com.gss.model.TagSum;

import java.sql.SQLException;
import java.util.List;

public class GetProductTagAction {
	
	private List<ProductTagReport> report;
	private List<TagSum> tagSum;
	
	public String execute() throws SQLException{
		
		this.report = ProductTagReport.getProductTagReport();
		this.tagSum = TagSum.getTagSum();
		
		return "success";
	}

	public List<ProductTagReport> getReport() {
		return report;
	}
	
	public List<TagSum> getTagSum() {
		return tagSum;
	}
}
