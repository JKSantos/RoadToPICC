package com.gss.actions.Reports;

import com.gss.model.ProductTagReport;

import java.sql.SQLException;
import java.util.List;

public class GetProductTagAction {
	
	private List<ProductTagReport> report;
	
	public String execute() throws SQLException{
		
		this.report = ProductTagReport.getProductTagReport();
		
		return "success";
	}

	public List<ProductTagReport> getReport() {
		return report;
	}
}
