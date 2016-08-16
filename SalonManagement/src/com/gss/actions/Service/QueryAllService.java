package com.gss.actions.Service;

import java.util.List;

import com.gss.model.Service;

public class QueryAllService {
	
	private List<Service> serviceList;
	
	public String execute(){
		
		this.serviceList = Service.queryAllService();
		
		return "success";
	}

	public List<Service> getServiceList() {
		return serviceList;
	}
}
