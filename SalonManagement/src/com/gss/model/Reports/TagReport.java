package com.gss.model.Reports;

import java.util.List;

public class TagReport {
	
	private String strType; //Annually, Quarterly, Monthly
	private List<TagReportDetail> details;
	
	public TagReport(String strType, List<TagReportDetail> details) {
		super();
		this.strType = strType;
		this.details = details;
	}

	public String getStrType() {
		return strType;
	}

	public void setStrType(String strType) {
		this.strType = strType;
	}

	public List<TagReportDetail> getDetails() {
		return details;
	}

	public void setDetails(List<TagReportDetail> details) {
		this.details = details;
	}
}
