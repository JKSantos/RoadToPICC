package com.gss.actions.ProductSales;

import java.util.List;

import com.gss.model.Location;
import com.gss.service.LocationServiceImpl;

public class ProductSalesMaintenance {
	
	private List<Location> locationList;
	
	public String execute(){
		
		LocationServiceImpl service = new LocationServiceImpl();
		
		this.locationList = service.getAllLocation();
		
		return "success";
	}

	public List<Location> getLocationList() {
		return locationList;
	}
}
