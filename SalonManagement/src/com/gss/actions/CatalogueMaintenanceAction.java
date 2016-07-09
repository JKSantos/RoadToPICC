package com.gss.actions;

import java.util.List;

import com.gss.model.Catalogue;
import com.gss.model.Service;
import com.gss.service.CatalogueService;
import com.gss.service.CatalogueServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;

public class CatalogueMaintenanceAction {
	
	private List<Catalogue> catList;
	private List<Service> serviceList;
	
	public String execute(){
		
		CatalogueService service = new CatalogueServiceImpl();
		ServiceService serv = new ServiceServiceImpl();
		this.setCatList(service.getAllCatalogue());
		this.setServiceList(serv.getAllService());
		
		return "success";
	}

	public List<Catalogue> getCatList() {
		return catList;
	}

	public void setCatList(List<Catalogue> catList) {
		this.catList = catList;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}
	
	

}
