package com.gss.model.Reports;

import java.util.List;

public class SalesReport {
	
	private String type;
	private List<SalesReportDetail> details;

	public SalesReport(String type, List<SalesReportDetail> details) {
		super();
		this.type = type;
		this.details = details;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<SalesReportDetail> getDetails() {
		return details;
	}
	public void setDetails(List<SalesReportDetail> details) {
		this.details = details;
	}
}
