package com.gss.actions.Location;

import java.util.List;

import com.gss.model.Location;
import com.gss.service.LocationService;
import com.gss.service.LocationServiceImpl;

public class GetAllLocation {
	
	private List<Location> locationList;
	
	public String execute(){
		
		LocationService service = new LocationServiceImpl();
		this.locationList = service.getAllLocation();
		
		return "success";
	}

	public List<Location> getLocationList() {
		return locationList;
	}

}
