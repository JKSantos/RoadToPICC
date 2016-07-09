package com.gss.actions;

import com.gss.model.Location;
import com.gss.service.LocationService;
import com.gss.service.LocationServiceImpl;

public class CreateLocationMaintenanceAction {
	
	private String strBrgy;
	private String strCity;
	private double dblLocationPrice;
	
	public String execute(){
		
		LocationService service = new LocationServiceImpl();
		Location location = new Location(1, this.strBrgy, this.strBrgy, this.dblLocationPrice, 1);
		
		if(service.createLocation(location) == true)
			return "success";
		else
			return "failed";
	}

	public double getDblLocationPrice() {
		return dblLocationPrice;
	}

	public void setDblLocationPrice(double dblLocationPrice) {
		this.dblLocationPrice = dblLocationPrice;
	}

	public String getStrBrgy() {
		return strBrgy;
	}

	public void setStrBrgy(String strBrgy) {
		this.strBrgy = strBrgy;
	}

	public String getStrCity() {
		return strCity;
	}

	public void setStrCity(String strCity) {
		this.strCity = strCity;
	}
	

}
