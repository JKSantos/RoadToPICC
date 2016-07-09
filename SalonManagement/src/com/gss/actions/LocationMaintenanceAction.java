package com.gss.actions;
import java.util.List;

import com.gss.model.Location;
import com.gss.service.LocationService;
import com.gss.service.LocationServiceImpl;

public class LocationMaintenanceAction {
	
	private List<Location> locationList;
	
	public String execute(){
		
		LocationService service = new LocationServiceImpl();
		this.locationList = service.getAllLocation();
		
		return "success";
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}
	
}
