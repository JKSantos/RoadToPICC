package com.gss.actions;

import com.gss.model.Location;
import com.gss.service.LocationService;
import com.gss.service.LocationServiceImpl;
import com.gss.utilities.PriceFormatHelper;

public class CreateLocationMaintenanceAction {
	
	private String strBrgy;
	private String strCity;
	private String price;
	
	public String execute() throws Exception{
		
		double dblLocationPrice = PriceFormatHelper.convertToDouble(price, "Php ");
		LocationService service = new LocationServiceImpl();
		Location location = new Location(1, this.strBrgy, this.strCity, dblLocationPrice, 1);
		
		if(service.createLocation(location) == true)
			return "success";
		else
			return "failed";
	}

	public String getStrBrgy() {
		return strBrgy;
	}

	public void setStrBrgy(String strBrgy) {
		this.strBrgy = strBrgy.trim().toUpperCase();
	}

	public String getStrCity() {
		return strCity;
	}

	public void setStrCity(String strCity) {
		this.strCity = strCity.trim().toUpperCase();
	}

	public void setPrice(String price) {
		this.price = price;
	}
	

}
