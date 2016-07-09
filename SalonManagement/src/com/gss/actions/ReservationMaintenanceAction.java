package com.gss.actions;

import java.util.List;

import com.gss.model.Service;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;

public class ReservationMaintenanceAction {
	
	private List<Service> serviceList;
	private List<Package> packageList;
	
	public String execute(){
		
		ServiceService service = new ServiceServiceImpl();
		this.serviceList = service.getAllService();
		
		return "success";
		
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}

	public List<Package> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<Package> packageList) {
		this.packageList = packageList;
	}

}
