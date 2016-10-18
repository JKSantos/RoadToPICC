package com.gss.actions.Reports.ServiceAvailment;

import java.util.List;

import com.gss.dao.Reports.ServiceAvailmentRepository;
import com.gss.model.Reports.ServiceAvailment;
import com.gss.utilities.ReportDate;

public class GetServiceAvailment {

	
	private List<ServiceAvailment> services;
	
	private String dateFrom;
	private String dateTo;
	
	public String execute(){
		ReportDate date = new ReportDate(dateFrom, dateTo);
		services = ServiceAvailmentRepository.getServiceAvailed(date);
		
		return "success";
	}
	
	public List<ServiceAvailment> getServices() {
		return services;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
}
