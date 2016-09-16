package com.gss.actions.Service;

import java.util.List;

import com.gss.dao.ServiceJDBCRepository;
import com.gss.model.Service;

public class GetServiceByType {
	
	public List<Service> serviceList;
	
	public String type;
	
	public String execute(){
		this.serviceList = ServiceJDBCRepository.getServiceByType(type);
		
		return "success";
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public void setType(String type) {
		this.type = type;
	}
}
