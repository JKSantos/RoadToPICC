package com.gss.actions.Service;

import java.util.List;

import com.gss.model.Service;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;

public class GetAllService {
	
	public String result = "success";
	public List<Service> serviceList;
	
	public String execute(){
		
		ServiceService service = new ServiceServiceImpl();
		this.serviceList = service.getAllServiceNoImage();
				
		return result;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}
}
